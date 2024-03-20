package com.core.domain.model.match

data class GetOpponentWrapper(
    val opponent: GetOpponent
) {
    data class GetOpponent(
        val id: Int,
        val imageUrl: String?,
        val name: String,
        val slug: String,
    )
}