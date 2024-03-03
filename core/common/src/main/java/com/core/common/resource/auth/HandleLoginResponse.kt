package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HandleLoginResponse @Inject constructor() {
    fun apiCall(call: suspend () -> AuthResult): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading(loading = true))
        try {
            val authResult = call()
            val user = authResult.user ?: throw FirebaseAuthInvalidUserException("ERROR_NO_SIGNED_IN_USER", "The user is not signed in")
            emit(Resource.Success(data = user))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(Resource.Error("The password is invalid or the user does not have a password."))
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(Resource.Error(e.localizedMessage ?: "User does not exist."))
        } catch (e: Exception) {
            emit(Resource.Error("Login failed: ${e.message ?: "Unknown error"}"))
        } finally {
            emit(Resource.Loading(loading = false))
        }
    }
}