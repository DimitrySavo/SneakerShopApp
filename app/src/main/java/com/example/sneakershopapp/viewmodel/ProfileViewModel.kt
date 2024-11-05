package com.example.sneakershopapp.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sneakershopapp.model.User
import com.example.sneakershopapp.utils.ValidationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel() : ViewModel() {
    private val _profileImageUri = MutableStateFlow<Uri?>(null)
    val profileImageUri = _profileImageUri.asStateFlow()

    private val _nameError = MutableStateFlow<String>("")
    val nameError = _nameError.asStateFlow()

    private val _surnameError = MutableStateFlow<String>("")
    val surnameError = _surnameError.asStateFlow()

    private val _emailError = MutableStateFlow<String>("")
    val emailError = _emailError.asStateFlow()

    private val _phoneNumberError = MutableStateFlow("")
    val phoneNumberError = _phoneNumberError.asStateFlow()

    fun changeProfileImageUri(newUri: Uri?) {
        _profileImageUri.value = newUri
    }

    private fun validateName(name: String): Boolean {
        _nameError.value = ValidationUtils.validateName(name)
        return _nameError.value.isEmpty()
    }

    private fun validateSurname(surname: String): Boolean {
        _surnameError.value = ValidationUtils.validateSurname(surname)
        return _surnameError.value.isEmpty()
    }

    private fun validateEmail(email: String): Boolean {
        _emailError.value = ValidationUtils.validateEmail(email)
        return _emailError.value.isEmpty()
    }

    private fun validatePhone(phone: String): Boolean {
        _phoneNumberError.value = ValidationUtils.validatePhoneNumber(phone)
        return _phoneNumberError.value.isEmpty()
    }

    fun validations(user: User): Boolean {
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
        } or validatePhone(user.phoneNumber ?: "").also {
            Log.i(
                "validations",
                "phone number is $it"
            )
        }
    }
}