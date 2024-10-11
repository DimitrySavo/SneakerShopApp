package com.example.sneakershopapp.model

sealed class LoginSate{
    data object Success : LoginSate()
    data class Error(val message : String) : LoginSate()
    data object Loading : LoginSate()
}
