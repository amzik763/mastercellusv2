package com.amzi.mastercellusv2.utility

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object EncryptionUtil {
    private const val AES_MODE = "AES/GCM/NoPadding"
    private const val KEY_ALIAS = "MyAppAESKey"
    private const val KEY_SIZE = 256
    private const val IV_SIZE = 12 // GCM standard for AES
    private const val TAG_SIZE = 128

    // Initialize KeyStore and generate the key if not present
    private fun getSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }

        // Check if the key already exists
        if (!keyStore.containsAlias(KEY_ALIAS)) {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            keyGenerator.init(
                KeyGenParameterSpec.Builder(KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(KEY_SIZE)
                    .build()
            )
            keyGenerator.generateKey()
        }

        return (keyStore.getKey(KEY_ALIAS, null) as SecretKey)
    }

    // Encrypt the data
    fun encrypt(data: String): String {
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())

        val ivBytes = cipher.iv // Generate IV
        val encryptedBytes = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        val encryptedData = ivBytes + encryptedBytes // Prepend IV to encrypted data
        return Base64.encodeToString(encryptedData, Base64.DEFAULT)
    }

    // Decrypt the data
    fun decrypt(encryptedData: String): String {
        val cipher = Cipher.getInstance(AES_MODE)
        val decodedData = Base64.decode(encryptedData, Base64.DEFAULT)

        // Extract IV
        val iv = decodedData.copyOfRange(0, IV_SIZE)
        val encryptedBytes = decodedData.copyOfRange(IV_SIZE, decodedData.size)

        val gcmSpec = GCMParameterSpec(TAG_SIZE, iv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), gcmSpec)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes, Charsets.UTF_8)
    }
}
