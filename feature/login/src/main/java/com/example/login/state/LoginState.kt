package com.example.login.state

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val isLoading: Boolean = false,
    val firebaseUser: FirebaseUser? = null,
    val errorMessage: String? = null
)
