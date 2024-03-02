package com.core.domain.model.category

data class GetProduct(
    val getCategory: GetCategory,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)