package com.feature.home.state

import com.feature.home.model.League
import com.feature.home.model.Product
import com.feature.home.model.auth.Users

data class HomeState(
    val categories: List<League>? = emptyList(),
    val products: List<Product>? = emptyList(),
    val user: Users? = null,
    val isLoading: Boolean = false,
    val imageUri: String? = null,
    val imageFetched: Boolean = false,
    val errorMessage: String? = null
)
