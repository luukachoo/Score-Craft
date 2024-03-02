package com.feature.home.state

import com.feature.home.model.League
import com.feature.home.model.Product

data class HomeState(
    val categories: List<League>? = emptyList(),
    val products: List<Product>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
