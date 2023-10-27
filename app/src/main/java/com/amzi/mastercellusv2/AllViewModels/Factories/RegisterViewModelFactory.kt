package com.amzi.mastercellusv2.AllViewModels.Factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.Repository.AuthRepo

class RegisterViewModelFactory(private val authRepo: AuthRepo) : ViewModelProvider.Factory {


        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(authRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
