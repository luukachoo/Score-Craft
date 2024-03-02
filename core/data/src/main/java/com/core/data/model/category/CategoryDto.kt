package com.core.data.model.category

import com.squareup.moshi.Json

data class CategoryDto(
    @Json(name = "creationAt")
    val creationAt: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)