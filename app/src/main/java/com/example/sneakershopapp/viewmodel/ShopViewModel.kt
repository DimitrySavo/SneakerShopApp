package com.example.sneakershopapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakershopapp.SneakerApplication
import com.example.sneakershopapp.model.CartShoe
import com.example.sneakershopapp.model.DataService
import com.example.sneakershopapp.model.FunctionResult
import com.example.sneakershopapp.model.Shoe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShopViewModel() : ViewModel(){
    private val dataService: DataService = SneakerApplication.getInstance().dataService

    private val _shoes = MutableStateFlow(emptyList<Shoe>())
    val shoes = _shoes.asStateFlow()

    private val _filteredShoes = MutableStateFlow(emptyList<Shoe>())
    val filteredShoes = _filteredShoes.asStateFlow()

    private val _favorites = MutableStateFlow(emptyList<String>())
    val favorites = _favorites.asStateFlow()

    fun getShoes() = viewModelScope.launch {
        when(val shoes = dataService.getShoes()){
            is FunctionResult.Success -> {
                Log.i("getShoes", "Shoes")
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
        getFavorites()
    }

    fun unmarkShoeAsFavorite(shoeId: String = "ET96Bi8yKUJzOEGHCCCI") = viewModelScope.launch {
        dataService.unmarkShoeAsFavorite(shoeId)
        getFavorites()
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