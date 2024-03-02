package com.core.domain.use_case.auth

import com.core.common.resource.Resource
import com.core.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetForgotPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String): Flow<Resource<Unit>> =
        authRepository.forgotPassword(email)
}