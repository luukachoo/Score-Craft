package com.core.domain.use_case.auth

import javax.inject.Inject

data class GetAuthUseCase @Inject constructor(
    val getLoginUseCase: GetLoginUseCase,
    val getRegisterUseCase: GetRegisterUseCase,
    val getLogOutUseCase: GetLogOutUseCase,
    val getForgotPasswordUseCase: GetForgotPasswordUseCase,
)
