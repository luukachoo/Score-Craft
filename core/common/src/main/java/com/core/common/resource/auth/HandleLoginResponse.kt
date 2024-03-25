package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HandleLoginResponse @Inject constructor() {
    fun apiCall(call: suspend () -> AuthResult): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading(loading = true))
        try {
            val authResult = call()
            val user = authResult.user ?: throw FirebaseAuthInvalidUserException(
                "ERROR_NO_SIGNED_IN_USER",
                "The user is not signed in."
            )
            emit(Resource.Success(data = user))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(Resource.Error("The email address or password is incorrect."))
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(Resource.Error("The user account does not exist or has been disabled."))
        } catch (e: FirebaseAuthUserCollisionException) {
            emit(Resource.Error("An account already exists with the given email address."))
        } catch (e: FirebaseTooManyRequestsException) {
            emit(Resource.Error("Too many requests. Try again later."))
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            emit(Resource.Error("Recent login required. Please log in again to perform this action."))
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error("Authentication error: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(Resource.Error("Login failed: ${e.message ?: "An unknown error occurred."}"))
        } finally {
            emit(Resource.Loading(loading = false))
        }
    }
}
