package com.example.tournament.model

data class OpponentWrapper(
    val opponent: Opponent
) {
    data class Opponent(
        val id: Int,
        val imageUrl: String?,
        val name: String,
        val slug: String,
    )
}