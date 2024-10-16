package com.example.sneakershopapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sneakershopapp.utils.ValidationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmailForForgotPass() : ViewModel() {
    private val _emailError = MutableStateFlow("")
    val emailError = _emailError.asStateFlow()

    private val _mailState = MutableStateFlow<Boolean?>(null)
    val mailState = _mailState.asStateFlow()

    fun validateEmail(email : String) {
        if (!ValidationUtils.isEmailValid(email)) {
            _emailError.value = "Неверный формат email"
        }
    }

    fun updateMailState(isSuccess: Boolean) {
        _mailState.value = isSuccess
    }

    fun resetMailState() {
        _mailState.value = null
    }

    fun resetErrorMessage() {
        _emailError.value = ""
    }
}