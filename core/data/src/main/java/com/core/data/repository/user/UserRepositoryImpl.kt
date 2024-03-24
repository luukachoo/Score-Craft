package com.core.data.repository.user

import android.content.Context
import android.net.Uri
import com.core.common.resource.Resource
import com.core.common.resource.auth.HandleCurrentUserResponse
import com.core.common.resource.auth.HandleSessionResponse
import com.core.data.worker_util.WorkManagerUtil
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val handleCurrentUserResponse: HandleCurrentUserResponse,
    private val handleSessionResponse: HandleSessionResponse,
) : UserRepository {
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
                            avatar = userDetailsMap["avatar"] ?: ""
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
                WorkManagerUtil.enqueueUploadWork(context, userId, imageUri)
                emit(Resource.Success("Image upload enqueued successfully"))
            } catch (e: Exception) {
                emit(Resource.Error("Failed to enqueue image upload: ${e.localizedMessage}"))
            } finally {
                emit(Resource.Loading(false))
            }
        }

    override suspend fun checkUserSession(): Flow<Resource<Boolean>> {
        return handleSessionResponse.checkUserSession()
    }
}