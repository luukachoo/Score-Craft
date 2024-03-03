package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HandleUserRegistrationResponse @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) {
    fun registerAndSaveUser(
        userName: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading(loading = true))
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: throw FirebaseAuthInvalidUserException("ERROR_USER_NOT_FOUND", "No Firebase User returned after registration.")

            val userToSave = mapOf("userName" to userName, "firstName" to firstName, "lastName" to lastName, "email" to email)
            firebaseDatabase.getReference("Users").child(firebaseUser.uid).setValue(userToSave).await()

            emit(Resource.Success(data = firebaseUser))
        } catch (e: FirebaseAuthUserCollisionException) {
            emit(Resource.Error("An account already exists with the same email address."))
        } catch (e: Exception) {
            emit(Resource.Error("Registration failed: ${e.message ?: "An unknown error occurred"}"))
        } finally {
            emit(Resource.Loading(loading = false))
        }
    }
}