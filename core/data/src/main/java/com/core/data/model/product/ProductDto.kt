package com.core.data.model.product

import com.core.data.model.category.CategoryDto
import com.squareup.moshi.Json

data class ProductDto(
    @Json(name = "category")
    val categoryDto: CategoryDto,
    @Json(name = "creationAt")
    val creationAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "images")
    val images: List<String>,
    @Json(name = "price")
    val price: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)