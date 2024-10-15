package com.example.sneakershopapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel() : ViewModel() {
    private val _emailError = MutableStateFlow("")
    val emailError = _emailError.asStateFlow()

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState = _loginState.asStateFlow()

    fun changeEmailMessage(isDataError: Boolean) {
        if (isDataError) _emailError.value = "Неверный email или пароль"
        else _emailError.value = "Произошла ошибка. Попробуйте позже или обратитесь в поддержку"
    }

    fun changeLoginState(isSuccess: Boolean) {
        _loginState.value = isSuccess
    }

    fun resetLoginState() {
        _loginState.value = null
    }

    fun resetErrorMessages() {
        _emailError.value = ""
    }
}