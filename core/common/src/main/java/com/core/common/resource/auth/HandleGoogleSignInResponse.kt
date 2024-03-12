package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HandleGoogleSignInResponse @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    suspend fun signInWithGoogle(idToken: String): Flow<Resource<FirebaseUser>> = flow {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            authResult.user?.let { user ->
                emit(Resource.Success(user))
            } ?: emit(Resource.Error("Failed to sign in with Google: No user found"))
        } catch (e: Exception) {
            emit(Resource.Error("Google sign-in failed: ${e.message}"))
        }
    }
}
