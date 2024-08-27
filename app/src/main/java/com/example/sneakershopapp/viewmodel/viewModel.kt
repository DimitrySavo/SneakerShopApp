package com.example.sneakershopapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopapp.model.CartShoe
import com.example.sneakershopapp.model.DataService
import com.example.sneakershopapp.model.FunctionResult
import com.example.sneakershopapp.model.Shoe
import com.example.sneakershopapp.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel(private val dataService: DataService = DataService()): ViewModel() {
    private val _shoes = MutableStateFlow(emptyList<Shoe>())
    val shoes = _shoes.asStateFlow()

    private val _user = MutableStateFlow(User("", "", ""))
    val user = _user.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _favorites = MutableStateFlow(emptyList<String>())
    val favorites = _favorites.asStateFlow()


    fun getShoes() = viewModelScope.launch {
        when(val shoes = dataService.getShoes()){
            is FunctionResult.Success -> {
                _shoes.value = shoes.data
            }
            is FunctionResult.Error -> {
                Log.e("MyViewModel", shoes.message)
            }
        }
    }

    fun getFavorites() = viewModelScope.launch {
        when(val favorites = dataService.getFavorites()){
            is FunctionResult.Success -> {
                _favorites.value = favorites.data
            }
            is FunctionResult.Error -> {
                Log.e("MyViewModel", favorites.message)
            }
        }
    }

    fun markShoeAsFavorite(shoeId: String = "ET96Bi8yKUJzOEGHCCCI") = viewModelScope.launch {
        dataService.markShoeAsFavorite(shoeId)
    }

    fun unmarkShoeAsFavorite(shoeId: String = "ET96Bi8yKUJzOEGHCCCI") = viewModelScope.launch {
        dataService.unmarkShoeAsFavorite(shoeId)
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
                Log.i("User uid", userUid.data)
            }
            is FunctionResult.Error -> {
                Log.e("MyViewModel", userUid.message)
            }
        }
    }

    fun addToCart(shoeId: String, shoeToAdd: CartShoe = CartShoe(size = "size-10")) = viewModelScope.launch {
        when(val result = dataService.addToCart(shoeId, shoeToAdd)){
            is FunctionResult.Success -> {
                Log.i("MyViewModel", "Shoe added to cart successfully")
            }
            is FunctionResult.Error -> {
                Log.e("MyViewModel", result.message)
            }
        }
    }

}