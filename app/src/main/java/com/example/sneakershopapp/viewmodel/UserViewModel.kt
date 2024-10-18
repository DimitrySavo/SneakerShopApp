package com.example.sneakershopapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopapp.SneakerApplication
import com.example.sneakershopapp.model.DataService
import com.example.sneakershopapp.model.FunctionResult
import com.example.sneakershopapp.model.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val dataService: DataService = SneakerApplication.getInstance().dataService) :
    ViewModel() {
    private val _user = MutableStateFlow(User("", "", ""))
    val user = _user.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _userUid = MutableStateFlow<String?>(null)
    val userUid = _userUid.asStateFlow()

    private var _otpCode: String? = null

    private val _otpCodeTimer = MutableStateFlow(0L)
    val otpCodeTimer = _otpCodeTimer.asStateFlow()


    private var timerJob: Job? = null

    init {
        Log.i("UserViewModel init", "Get into init block of userViewModel")
        getUserUid()
    }

    fun updateUser(
        email: String? = null,
        name: String? = null,
        surname: String? = null,
        phoneNumber: String? = null,
        deliveryAddress: String? = null
    ) {
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

    fun loginUser(loginViewModel : LoginViewModel) = viewModelScope.launch {
        when (val result = dataService.loginUser(_user.value.email, _password.value)) {
            is FunctionResult.Success -> loginViewModel.changeLoginState(true)
            is FunctionResult.Error -> {
                loginViewModel.changeEmailMessage(result.message.contains("Ошибка аутентификации"))
                loginViewModel.changeLoginState(false)
            }
        }
    }

    fun logoutUser() = dataService.logoutUser()

    fun registerUser(registerViewModel: RegisterViewModel) = viewModelScope.launch {
        when (val result = dataService.registerUser(_user.value, _password.value)) {
            is FunctionResult.Success -> {
                registerViewModel.updateRegisterState(true)
                Log.i("Register", "User ${result.data} successfully registered")
            }
            is FunctionResult.Error -> {
                registerViewModel.updateRegisterState(false)
                when (result.message) {
                    "Email is already in use" -> {
                        registerViewModel.updateEmailMessage("Пользователь с таким email уже существует")
                        Log.e("Register", "Пользователь с таким email уже существует")
                    }
                    else -> Log.e("Register", result.message)
                }
            }
        }
    }

    fun getUserUid() = viewModelScope.launch {
        when (val userUid = dataService.getUserUid()) {
            is FunctionResult.Success -> {
                Log.i("User uid", userUid.data ?: "User is null")
                _userUid.value = userUid.data
            }

            is FunctionResult.Error -> {
                Log.e("MyViewModel", userUid.message)
            }
        }
    }

    fun isUserRegistered(): Boolean {
        return when (val userUid = dataService.getUserUid()) {
            is FunctionResult.Success -> {
                userUid.data != null
            }

            is FunctionResult.Error -> {
                throw IllegalStateException(userUid.message)
            }
        }
    }

    fun passwordReset(userEmail: String = user.value.email, setErrorMessage: (String) -> Unit, changeEmailSentStatus: (Boolean) -> Unit) = viewModelScope.launch {
        when (val result = dataService.sendResetPasswordEmail(userEmail)) {
            is FunctionResult.Success -> {
                _otpCode = result.data
                _otpCodeTimer.value = 300000
                Log.i("UserViewModel", "Код успешно сгенерирован и отправлен. Код - ${_otpCode}.")
                startOtpTimer()
                changeEmailSentStatus(true)
            }

            is FunctionResult.Error -> {
                if (result.message == "There is no user with such email") {
                    Log.e("UserViewModel", "Пользователя с таким email не существует")
                    setErrorMessage("Проверьте правильность ввода email")
                    changeEmailSentStatus(false)
                } else {
                    Log.e("UserViewModel", "Что-то пошло не так при попытке отправить otp код")
                    setErrorMessage("Произошла ошибка при отправке кода")
                    changeEmailSentStatus(false)
                }
            }
        }
    }

    fun checkOtpCode(otpCode: String, onSuccess: () -> Unit, onError: () -> Unit) {
        if (otpCode == _otpCode) {
            onSuccess()
        } else {
            onError()
        }
    }

    private fun startOtpTimer() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            while (_otpCodeTimer.value > 0) {
                delay(1000L)
                _otpCodeTimer.value -= 1000L
            }
            _otpCode = null
        }
    }

    private fun stopOtpTimer() {
        timerJob?.cancel()
        _otpCode = null
        _otpCodeTimer.value = 0L
    }
}