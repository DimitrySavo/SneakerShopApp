package com.example.sneakershopapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakershopapp.SneakerApplication
import com.example.sneakershopapp.model.DataService
import com.example.sneakershopapp.model.FunctionResult
import com.example.sneakershopapp.model.LoginSate
import com.example.sneakershopapp.model.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val dataService: DataService = SneakerApplication.getInstance().dataService) : ViewModel() {
    private val _user = MutableStateFlow(User("", "", ""))
    val user = _user.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _userUid = MutableStateFlow<String?>(null)
    val userUid = _userUid.asStateFlow()

    private val _otpCode = MutableStateFlow<String?>(null)
    val otpCode = _otpCode.asStateFlow()

    private val _otpCodeTimer = MutableStateFlow(0L)
    val otpCodeTimer = _otpCodeTimer.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _loginState = MutableStateFlow<LoginSate>(LoginSate.Loading)
    val loginState = _loginState.asStateFlow()

    private var timerJob: Job? = null

    init {
        Log.i("UserViewModel init", "Get into init block of userViewModel")
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

    fun loginUser() = viewModelScope.launch {
        when(val result = dataService.loginUser(_user.value.email, _password.value)){
            is FunctionResult.Success -> _loginState.value = LoginSate.Success
            is FunctionResult.Error -> _loginState.value = LoginSate.Error(result.message)
        }
    }

    fun logoutUser() = dataService.logoutUser()

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

    fun isUserRegistered() : Boolean {
        return when(val userUid = dataService.getUserUid()){
            is FunctionResult.Success -> {
                userUid.data != null
            }
            is FunctionResult.Error -> {
                throw IllegalStateException(userUid.message)
            }
        }
    }

    fun passwordReset(userEmail: String) = viewModelScope.launch {
        when(val result = dataService.sendResetPasswordEmail(userEmail)) {
            is FunctionResult.Success -> {
                _otpCode.value = result.data
                _otpCodeTimer.value = 50000
                startOtpTimer()
            }
            is FunctionResult.Error -> {
                _errorMessage.emit(result.message)
                return@launch
            }
        }
    }

    private fun startOtpTimer() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            while (_otpCodeTimer.value > 0) {
                delay(1000L)
                _otpCodeTimer.value -= 1000L
            }
            _otpCode.value = null
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        _otpCode.value = null
        _otpCodeTimer.value = 0L
    }
}