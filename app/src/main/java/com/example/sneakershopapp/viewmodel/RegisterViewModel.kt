package com.example.sneakershopapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sneakershopapp.model.User
import com.example.sneakershopapp.utils.ValidationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel() : ViewModel() {
    private val _nameError = MutableStateFlow<String>("")
    val nameError = _nameError.asStateFlow()

    private val _surnameError = MutableStateFlow<String>("")
    val surnameError = _surnameError.asStateFlow()

    private val _emailError = MutableStateFlow<String>("")
    val emailError = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String>("")
    val passwordError = _passwordError.asStateFlow()

    private val _registerState = MutableStateFlow<Boolean?>(null)
    val registerState = _registerState.asStateFlow()

    fun validateName(name: String): Boolean {
        _nameError.value = ValidationUtils.validateName(name)
        return _nameError.value.isEmpty()
    }

    fun validateSurname(surname: String): Boolean {
        _surnameError.value = ValidationUtils.validateSurname(surname)
        return _surnameError.value.isEmpty()
    }

    fun validateEmail(email: String): Boolean {
        _emailError.value = ValidationUtils.validateEmail(email)
        return _emailError.value.isEmpty()
    }

    fun validatePassword(password: String): Boolean {
        _passwordError.value = ValidationUtils.validatePassword(password)
        return _passwordError.value.isEmpty()
    }

    fun updateEmailMessage(message: String) {
        _emailError.value = message
    }

    fun validations(user: User, password: String): Boolean {
        return validateName(user.name).also {
            Log.i(
                "validations",
                "name is $it"
            )
        } or validateSurname(user.surname).also {
            Log.i(
                "validations",
                "surname is $it"
            )
        } or validateEmail(user.email).also {
            Log.i(
                "validations",
                "email is $it"
            )
        } or validatePassword(password).also {
            Log.i(
                "validations",
                "password is $it"
            )
        }
    }

    fun updateRegisterState(isSuccess: Boolean) {
        _registerState.value = isSuccess
    }

    fun resetRegisterState() {
        _registerState.value = null
    }
}