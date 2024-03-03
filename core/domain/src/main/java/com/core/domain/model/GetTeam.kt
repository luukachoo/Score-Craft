package com.core.domain.model

data class GetTeam(
    val acronym: String,
    val id: Int,
    val imageUrl: String,
    val location: String,
    val modifiedAt: String,
    val name: String,
    val slug: String,
    val players: List<GetPlayer>
)
