package com.core.domain.model

data class GetPlayer (
    val active: Boolean,
    val age: Int,
    val birthday: String,
    val firstName: String,
    val id: Int,
    val imageUrl: String,
    val lastName: String,
    val name: String,
    val nationality: String,
    val role: String,
    val slug: String
)