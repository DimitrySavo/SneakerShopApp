package com.example.sneakershopapp.model

import com.example.sneakershopapp.utils.ValidationUtils
import kotlin.reflect.full.memberProperties

data class User(
    val email: String,
    val name: String,
    val surname: String,
    val phoneNumber: String? = null,
    val deliveryAddress: String? = null,
    var favorites: List<String> = emptyList(),
){
    constructor(): this(
        "",
        "",
        ""
    )
}

//Дописать функцию перевода в map<String, Any?>

inline fun<reified T: Any> T.toMap(): Map<String, Any?> {
    return T::class.memberProperties.associate { prop ->
        prop.name to prop.getValue(this, prop)
    }
}
