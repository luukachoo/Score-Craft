package com.feature.live_matches.model

data class Team(
    val acronym: String,
    val id: Int,
    val imageUrl: String,
    val location: String,
    val modifiedAt: String,
    val name: String,
    val slug: String,
    val players: List<Player>
)
