package com.core.domain.model

data class GetOpponentWithType(
    val opponent: Opponent,
    val type: String
) {
    data class Opponent(
        val acronym: String,
        val id: Int,
        val imageUrl: String,
        val location: String?,
        val modifiedAt: String,
        val name: String,
        val slug: String
    )
}