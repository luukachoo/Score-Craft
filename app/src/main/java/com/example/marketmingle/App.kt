package com.example.marketmingle

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}