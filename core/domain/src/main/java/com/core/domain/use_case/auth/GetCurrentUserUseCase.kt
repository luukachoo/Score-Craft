package com.core.domain.use_case.auth

import com.core.common.resource.Resource
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Flow<Resource<GetUsers>> = authRepository.getCurrentUser()
}