package com.feature.home.state

import com.feature.home.model.Category
import com.feature.home.model.Product

data class HomeState(
    val categories: List<Category>? = emptyList(),
    val products: List<Product>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
