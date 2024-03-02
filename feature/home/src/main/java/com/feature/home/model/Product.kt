package com.feature.home.model

data class Product(
    val league: League,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
)
