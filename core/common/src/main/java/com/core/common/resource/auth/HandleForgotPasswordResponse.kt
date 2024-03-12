package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HandleForgotPasswordResponse @Inject constructor() {
    suspend fun <T : Any> apiCall(apiCall: suspend () -> T): Flow<Resource<T>> = flow {
        try {
            val response = apiCall()
            emit(Resource.Success(response))
        } catch (e: FirebaseAuthActionCodeException) {
            emit(Resource.Error("Action code is invalid or expired."))
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(Resource.Error("There is no user record corresponding to this identifier. The user may have been deleted."))
        } catch (e: FirebaseAuthEmailException) {
            emit(Resource.Error("Email address is not valid or the user does not have an email address."))
        } catch (e: FirebaseException) {
            emit(Resource.Error("Firebase exception occurred: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.Error("An unknown error occurred: ${e.message}"))
        }
    }
}