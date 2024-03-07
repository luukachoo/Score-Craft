package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HandleSessionResponse @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    suspend fun checkUserSession(): Flow<Resource<Boolean>> = flow {
        try {
            val user = firebaseAuth.currentUser
            if (user != null) {
                user.getIdToken(true).await()
                emit(Resource.Success(true))
            } else {
                emit(Resource.Success(false))
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(Resource.Error("Session expired, please log in again"))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to check user session: ${e.message}"))
        }
    }
}