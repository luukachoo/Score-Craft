package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HandleUserRegistrationResponse @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseMessaging: FirebaseMessaging
) {
    fun registerAndSaveUser(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading(true))
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: throw FirebaseAuthInvalidUserException(
                "ERROR_USER_NOT_FOUND",
                "No Firebase User returned after registration."
            )

            val fcmToken = firebaseMessaging.token.await()

            val userToSave = mapOf(
                "userName" to userName,
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "fcmToken" to fcmToken
            )
            firebaseDatabase.getReference("Users").child(firebaseUser.uid).setValue(userToSave)
                .await()

            emit(Resource.Success(firebaseUser))
        } catch (e: FirebaseAuthUserCollisionException) {
            emit(Resource.Error("An account already exists with the same email address."))
        } catch (e: Exception) {
            emit(Resource.Error("Registration failed: ${e.message ?: "An unknown error occurred"}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}