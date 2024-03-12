package com.example.registration.state

import com.google.firebase.auth.FirebaseUser

data class RegisterState(
    val isLoading: Boolean = false,
    val firebaseUser: FirebaseUser? = null,
    val errorMessage: String? = null
)
