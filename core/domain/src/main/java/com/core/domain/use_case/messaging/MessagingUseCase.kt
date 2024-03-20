package com.core.domain.use_case.messaging

import javax.inject.Inject

data class MessagingUseCase @Inject constructor(
    val getSendMessageUseCase: GetSendMessageUseCase,
    val getFetchMessagesUseCase: GetFetchMessagesUseCase
)
