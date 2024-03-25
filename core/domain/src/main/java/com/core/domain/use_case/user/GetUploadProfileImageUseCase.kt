package com.core.domain.use_case.user

import android.net.Uri
import com.core.domain.repository.user.UserRepository
import javax.inject.Inject

class GetUploadProfileImageUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: String, imageUri: Uri) =
        userRepository.uploadProfileImage(userId = userId, imageUri = imageUri)
}