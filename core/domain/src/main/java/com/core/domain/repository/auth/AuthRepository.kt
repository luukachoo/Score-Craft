package com.core.domain.repository.auth

import com.core.common.resource.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(userName: String, firstName: String, lastName: String, email: String, password: String): Flow<Resource<FirebaseUser>>
    suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>>
    suspend fun logOut()
    suspend fun forgotPassword(email: String): Flow<Resource<Unit>>
}