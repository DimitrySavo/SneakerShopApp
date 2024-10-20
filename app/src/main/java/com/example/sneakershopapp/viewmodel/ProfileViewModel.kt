package com.example.sneakershopapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel() : ViewModel() {
    private val _nameError = MutableStateFlow<String>("")
    val nameError = _nameError.asStateFlow()

    private val _surnameError = MutableStateFlow<String>("")
    val surnameError = _surnameError.asStateFlow()

    private val _emailError = MutableStateFlow<String>("")
    val emailError = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String>("")
    val passwordError = _passwordError.asStateFlow()

    private val _phoneNumberError = MutableStateFlow("")
    val phoneNumberError = _phoneNumberError.asStateFlow()


}