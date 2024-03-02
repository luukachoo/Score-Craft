package com.core.domain.use_case.auth

import com.core.common.resource.Resource
import com.core.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) : Flow<Resource<FirebaseUser>> = authRepository.login(email, password)
}