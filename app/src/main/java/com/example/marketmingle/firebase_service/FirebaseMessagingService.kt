package com.example.marketmingle.firebase_service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.example.marketmingle.R
import com.example.marketmingle.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        handleNotification(message)
    }

    private fun handleNotification(message: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("market-mingle://feature.friend_request/fragment_friend_request")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val channelId = getString(R.string.default_notification_channel_id)
        val notificationBuilder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle(message.notification?.title ?: "New Notification")
            setContentText(message.notification?.body ?: "You have new activity.")
            setAutoCancel(true)
            setContentIntent(pendingIntent)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(message.messageId.hashCode(), notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        updateTokenInDatabase(token)
    }

    private fun updateTokenInDatabase(newToken: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            FirebaseDatabase.getInstance().getReference("Users")
                .child(it)
                .child("fcmToken")
                .setValue(newToken)
        }
    }
}