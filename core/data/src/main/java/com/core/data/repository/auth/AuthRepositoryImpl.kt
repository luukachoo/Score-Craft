package com.core.data.repository.auth

import com.core.common.resource.auth.HandleLoginResponse
import com.core.common.resource.auth.HandleForgotPasswordResponse
import com.core.common.resource.auth.HandleUserRegistrationResponse
import com.core.common.resource.Resource
import com.core.common.resource.auth.HandleCurrentUserResponse
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val handleLoginResponse: HandleLoginResponse,
    private val handleForgotPasswordResponse: HandleForgotPasswordResponse,
    private val handleUserRegistrationResponse: HandleUserRegistrationResponse,
    private val handleCurrentUserResponse: HandleCurrentUserResponse
): AuthRepository {
    override suspend fun register(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> {
        return handleUserRegistrationResponse.registerAndSaveUser(userName, firstName, lastName, email, password)
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
                            userName = userDetailsMap["userName"] ?: "",
                            firstName = userDetailsMap["firstName"] ?: "",
                            lastName = userDetailsMap["lastName"] ?: "",
                            email = userDetailsMap["email"] ?: "",
                            password = userDetailsMap["password"] ?: ""
                        )
                        emit(Resource.Success(userDetails))
                    }
                }
            }
        } else {
            flow { emit(Resource.Error("No user logged in")) }
        }
    }
}