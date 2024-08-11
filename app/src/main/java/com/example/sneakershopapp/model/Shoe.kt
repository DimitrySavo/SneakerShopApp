package com.example.sneakershopapp.model

data class Shoe(
    val id: String,
    val brandName: String,
    val color: String,
    val description: String,
    val imageUrl: String,
    val modelName: String,
    val tags: List<String>,
    val sizes: List<Size>,
    val price: Double,
    val inStock: Boolean
)

data class Size(
    val usSize: Double,
    val euSize: Double,
    val ruSize: Double,
    val inStock: Long,
    val price: Double
)