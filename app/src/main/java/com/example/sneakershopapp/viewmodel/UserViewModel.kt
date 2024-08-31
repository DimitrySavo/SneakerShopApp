package com.example.sneakershopapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopapp.SneakerApplication
import com.example.sneakershopapp.model.DataService
import com.example.sneakershopapp.model.FunctionResult
import com.example.sneakershopapp.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val dataService: DataService = SneakerApplication.getInstance().dataService) : ViewModel() {
    private val _user = MutableStateFlow(User("", "", ""))
    val user = _user.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _userUid = MutableStateFlow<String?>(null)
    val userUid = _userUid.asStateFlow()

    init {
        getUserUid()
    }

    fun updateUser(email: String? = null, name: String? = null, surname: String? = null, phoneNumber: String? = null, deliveryAddress: String? = null) {
        _user.value = _user.value.copy(
            email = email ?: _user.value.email,
            name = name ?: _user.value.name,
            surname = surname ?: _user.value.surname,
            phoneNumber = phoneNumber ?: _user.value.phoneNumber,
            deliveryAddress = deliveryAddress ?: _user.value.deliveryAddress
        )
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun addUser() = viewModelScope.launch {
        dataService.registerUser(_user.value, _password.value)
    }

    fun getUserUid() = viewModelScope.launch {
        when(val userUid = dataService.getUserUid()){
            is FunctionResult.Success -> {
                Log.i("User uid", userUid.data ?: "User is null")
                _userUid.value = userUid.data
            }
            is FunctionResult.Error -> {
                Log.e("MyViewModel", userUid.message)
            }
        }
    }
}