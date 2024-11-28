package com.amzi.mastercellusv2.allViewModels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amzi.mastercellusv2.allViewModels.RegisterViewModel
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.HomeAutoRepo

class RegisterViewModelFactory(
    private val context: Context,
    private val authRepo: AuthRepo,
    private val homeAutoRepo: HomeAutoRepo
) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(context,authRepo, homeAutoRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
