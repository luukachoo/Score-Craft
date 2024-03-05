package com.feature.live_matches.model

data class OpponentWithType(
    val opponent: GetOpponent,
    val type: String
) {
    data class GetOpponent(
        val acronym: String,
        val id: Int,
        val imageUrl: String,
        val location: String,
        val modifiedAt: String,
        val name: String,
        val slug: String
    )
}
