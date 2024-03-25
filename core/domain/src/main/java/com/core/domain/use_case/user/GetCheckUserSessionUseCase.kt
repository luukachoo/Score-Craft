package com.core.domain.use_case.user

import com.core.domain.repository.user.UserRepository
import javax.inject.Inject

class GetCheckUserSessionUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.checkUserSession()
}