package com.core.data.model.send_notification

data class FCMResponseDTO(
    val success: Int,
    val failure: Int,
    val results: List<FCMResultDTO>
)