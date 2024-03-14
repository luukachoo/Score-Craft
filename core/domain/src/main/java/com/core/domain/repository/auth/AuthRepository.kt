package com.core.domain.repository.auth

import android.net.Uri
import com.core.common.resource.Resource
import com.core.domain.model.auth.GetUsers
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>>

    suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>>
    suspend fun logOut()
    suspend fun forgotPassword(email: String): Flow<Resource<Unit>>
    suspend fun getCurrentUser(): Flow<Resource<GetUsers>>
    suspend fun uploadProfileImage(userId: String, imageUri: Uri): Flow<Resource<String>>
    suspend fun fetchUserProfileImageUrl(userId: String): Flow<Resource<String>>
    suspend fun checkUserSession(): Flow<Resource<Boolean>>
    suspend fun saveFavouriteLeagues(leagueSlug: String): Flow<Resource<String>>
    suspend fun addFriend(userName: String) : Flow<Resource<String>>
}