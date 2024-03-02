package com.core.domain.model

data class GetVideoGame(
    val currentVersion: String?,
    val id: Int,
    val name: String,
    val slug: String
)
