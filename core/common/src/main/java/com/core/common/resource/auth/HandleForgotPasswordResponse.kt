package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HandleForgotPasswordResponse @Inject constructor() {
    suspend fun <T : Any> apiCall(apiCall: suspend () -> T): Flow<Resource<T>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = apiCall()
            emit(Resource.Success(response))
        } catch (e: FirebaseAuthActionCodeException) {
            emit(Resource.Error("Action code is invalid or expired."))
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(Resource.Error("There is no user record corresponding to this identifier. The user may have been deleted."))
        } catch (e: FirebaseAuthEmailException) {
            emit(Resource.Error("Email address is not valid or the user does not have an email address."))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error("Network error occurred. Please check your connection and try again."))
        } catch (e: FirebaseTooManyRequestsException) {
            emit(Resource.Error("Too many requests have been made to the server. Please try again later."))
        } catch (e: FirebaseException) {
            emit(Resource.Error("Firebase exception occurred: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.Error("An unknown error occurred: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}
