package com.core.domain.repository.user

import android.net.Uri
import com.core.common.resource.Resource
import com.core.domain.model.auth.GetUsers
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getCurrentUser(): Flow<Resource<GetUsers>>
    suspend fun uploadProfileImage(userId: String, imageUri: Uri): Flow<Resource<String>>
    suspend fun checkUserSession(): Flow<Resource<Boolean>>
}