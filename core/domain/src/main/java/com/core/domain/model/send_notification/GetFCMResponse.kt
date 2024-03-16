package com.core.domain.model.send_notification

data class GetFCMResponse(
    val success: Int,
    val failure: Int,
    val results: List<GetFCMResult>
)
