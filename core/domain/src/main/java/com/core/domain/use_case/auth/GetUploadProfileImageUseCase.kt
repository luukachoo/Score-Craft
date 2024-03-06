package com.core.domain.use_case.auth

import android.net.Uri
import com.core.domain.repository.auth.AuthRepository
import javax.inject.Inject

class GetUploadProfileImageUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userId: String, imageUri: Uri) =
        authRepository.uploadProfileImage(userId, imageUri)
}