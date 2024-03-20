package com.core.domain.model.league

data class GetTeam(
    val acronym: String,
    val id: Int,
    val imageUrl: String,
    val location: String,
    val name: String
)

