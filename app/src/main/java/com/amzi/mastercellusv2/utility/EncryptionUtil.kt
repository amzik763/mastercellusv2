import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import java.nio.charset.Charset
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec

object EncryptionUtil {
    private const val AES_MODE = "AES/GCM/NoPadding"
    private const val KEY_SIZE = 256
    private const val IV_SIZE = 12 // GCM standard for AES
    private const val TAG_SIZE = 128

    // Generate a random AES key
    fun generateAESKey(): Key {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(KEY_SIZE)
        return keyGen.generateKey()
    }

    // Encrypt the data
    fun encrypt(data: String, key: Key): String {
        val cipher = Cipher.getInstance(AES_MODE)
        val iv = ByteArray(IV_SIZE)
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val ivBytes = cipher.iv // Generate IV

        val encryptedBytes = cipher.doFinal(data.toByteArray(Charset.forName("UTF-8")))
        val encryptedData = ivBytes + encryptedBytes // Prepend IV to encrypted data
        return Base64.encodeToString(encryptedData, Base64.DEFAULT)
    }

    // Decrypt the data
    fun decrypt(encryptedData: String, key: Key): String {
        val cipher = Cipher.getInstance(AES_MODE)
        val decodedData = Base64.decode(encryptedData, Base64.DEFAULT)

        // Extract IV
        val iv = decodedData.copyOfRange(0, IV_SIZE)
        val encryptedBytes = decodedData.copyOfRange(IV_SIZE, decodedData.size)

        val gcmSpec = GCMParameterSpec(TAG_SIZE, iv)
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes, Charset.forName("UTF-8"))
    }
}

fun saveToken(context: Context, accessToken: String, refreshToken: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // Generate AES key (or load it from a secure place)
    val aesKey = EncryptionUtil.generateAESKey()
    // Encrypt the tokens
    val encryptedAccessToken = EncryptionUtil.encrypt(accessToken, aesKey)
    val encryptedRefreshToken = EncryptionUtil.encrypt(refreshToken, aesKey)

    // Save encrypted tokens to SharedPreferences
    editor.putString("ACCESS_TOKEN", encryptedAccessToken)
    editor.putString("REFRESH_TOKEN", encryptedRefreshToken)
    editor.apply()
    // Consider storing the AES key securely, for example, in the Android Keystore.
}

fun getToken(context: Context): Pair<String, String>? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    val encryptedAccessToken = sharedPreferences.getString("ACCESS_TOKEN", null)
    val encryptedRefreshToken = sharedPreferences.getString("REFRESH_TOKEN", null)

    return if (encryptedAccessToken != null && encryptedRefreshToken != null) {
        val aesKey = EncryptionUtil.generateAESKey() // Retrieve the stored AES key from secure storage
        // Decrypt the tokens
        val accessToken = EncryptionUtil.decrypt(encryptedAccessToken, aesKey)
        val refreshToken = EncryptionUtil.decrypt(encryptedRefreshToken, aesKey)
        Pair(accessToken, refreshToken)
    } else {
        null
    }
}