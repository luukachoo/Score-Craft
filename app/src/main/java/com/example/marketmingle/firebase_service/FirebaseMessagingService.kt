package com.example.marketmingle.firebase_service

import android.util.Log
import com.google.android.datatransport.runtime.logging.Logging.d
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCM Message", "From: ${message.from}")
        message.data.isNotEmpty().let {
            Log.d("FCM Data", "Message data payload: ${message.data}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM Token", "Refreshed token: $token")

        updateTokenInDatabase(token)
    }

    private fun updateTokenInDatabase(newToken: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            FirebaseDatabase.getInstance().getReference("Users")
                .child(it)
                .child("fcmToken")
                .setValue(newToken)
                .addOnSuccessListener {
                    d("Token Update", "Success")
                }
                .addOnFailureListener {
                    d("Token Update", "Failure")
                }
        }
    }
}

