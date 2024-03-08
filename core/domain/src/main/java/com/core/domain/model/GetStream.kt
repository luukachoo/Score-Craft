package com.core.domain.model

data class GetStream(
    val embedUrl: String?,
    val language: String,
    val main: Boolean,
    val official: Boolean,
    val rawUrl: String
)
