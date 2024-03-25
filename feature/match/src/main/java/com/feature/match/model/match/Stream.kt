package com.feature.match.model.match

data class Stream(
    val embedUrl: String?,
    val language: String,
    val main: Boolean,
    val official: Boolean,
    val rawUrl: String
)
