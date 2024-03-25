package com.core.domain.use_case.user

import javax.inject.Inject

data class GetUserUseCase @Inject constructor(
    val getCheckUserSessionUseCase: GetCheckUserSessionUseCase,
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val getUploadProfileImageUseCase: GetUploadProfileImageUseCase
)
