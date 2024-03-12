package com.core.data.repository.auth

import android.content.Context
import android.net.Uri
import com.core.common.resource.Resource
import com.core.common.resource.auth.HandleCurrentUserResponse
import com.core.common.resource.auth.HandleForgotPasswordResponse
import com.core.common.resource.auth.HandleLoginResponse
import com.core.common.resource.auth.HandleSessionResponse
import com.core.common.resource.auth.HandleUserRegistrationResponse
import com.core.data.worker_util.WorkManagerUtil.enqueueUploadWork
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val handleLoginResponse: HandleLoginResponse,
    private val handleForgotPasswordResponse: HandleForgotPasswordResponse,
    private val handleUserRegistrationResponse: HandleUserRegistrationResponse,
    private val handleCurrentUserResponse: HandleCurrentUserResponse,
    private val handleSessionResponse: HandleSessionResponse,
) : AuthRepository {
    override suspend fun register(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> {
        return handleUserRegistrationResponse.registerAndSaveUser(
            userName,
            firstName,
            lastName,
            email,
            password
        )
    }

    override suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>> {
        return handleLoginResponse.apiCall {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }

    override suspend fun logOut() {
        return firebaseAuth.signOut()
    }

    override suspend fun forgotPassword(email: String): Flow<Resource<Unit>> {
        return handleForgotPasswordResponse.apiCall {
            firebaseAuth.sendPasswordResetEmail(email)
        }
    }

    override suspend fun getCurrentUser(): Flow<Resource<GetUsers>> {
        val currentUser = firebaseAuth.currentUser
        return if (currentUser != null) {
            handleCurrentUserResponse.getUserDetails(currentUser.uid).transform { result ->
                when (result) {
                    is Resource.Loading -> emit(Resource.Loading(result.loading))
                    is Resource.Error -> emit(Resource.Error(result.errorMessage))
                    is Resource.Success -> {
                        val userDetailsMap = result.data
                        val userDetails = GetUsers(
                            userId = currentUser.uid,
                            userName = userDetailsMap["userName"] ?: "",
                            firstName = userDetailsMap["firstName"] ?: "",
                            lastName = userDetailsMap["lastName"] ?: "",
                            email = userDetailsMap["email"] ?: "",
                            password = userDetailsMap["password"] ?: "",
                        )
                        emit(Resource.Success(userDetails))
                    }
                }
            }
        } else {
            flow { emit(Resource.Error("No user logged in")) }
        }
    }

    override suspend fun uploadProfileImage(userId: String, imageUri: Uri): Flow<Resource<String>> =
        flow {
            emit(Resource.Loading(true))
            try {
                enqueueUploadWork(context, userId, imageUri)
                emit(Resource.Success("Upload scheduled"))
            } catch (e: Exception) {
                emit(Resource.Error("Failed to schedule upload: ${e.localizedMessage}"))
            }

            emit(Resource.Loading(false))
        }

    override suspend fun fetchUserProfileImageUrl(userId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))
        try {
            val storageRef =
                FirebaseStorage.getInstance().reference.child("user_profiles/$userId/profile_picture.jpg")
            val imageUrl = storageRef.downloadUrl.await()
            emit(Resource.Success(imageUrl.toString()))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to fetch image URL: ${e.message}"))
        }
        emit(Resource.Loading(false))
    }

    override suspend fun checkUserSession(): Flow<Resource<Boolean>> {
        return handleSessionResponse.checkUserSession()
    }

    override suspend fun saveFavouriteLeagues(leagueSlug: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))
        try {
            val userId = firebaseAuth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            val databaseReference = FirebaseDatabase.getInstance().getReference("UserLeagues").child(userId)
            val snapshot = databaseReference.child(leagueSlug).get().await()

            val message: String = if (snapshot.exists()) {
                databaseReference.child(leagueSlug).removeValue().await()
                "Removed from favourites"
            } else {
                databaseReference.child(leagueSlug).setValue("League").await()
                "Added to favourites"
            }

            emit(Resource.Success(message))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to update favourite league: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}