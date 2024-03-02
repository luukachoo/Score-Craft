package com.core.domain.use_case.auth

import com.core.common.resource.Resource
import com.core.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> =
        authRepository.register(
            userName = userName,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )
}