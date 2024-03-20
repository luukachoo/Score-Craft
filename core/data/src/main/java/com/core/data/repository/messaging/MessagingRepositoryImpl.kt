package com.core.data.repository.messaging

import com.core.common.resource.Resource
import com.core.domain.model.messaging.GetMessage
import com.core.domain.repository.send_message.MessagingRepository
import com.core.domain.use_case.auth.GetAuthUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth
) : MessagingRepository {

    override suspend fun sendMessage(receiverId: String, messageText: String): Flow<Resource<Unit>> = flow {
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            emit(Resource.Error("No authenticated user found"))
            return@flow
        }

        val sendId = currentUser.uid

        try {
            emit(Resource.Loading(true))

            val messagesRef = firebaseDatabase.getReference("Messages")
            val messageKey = messagesRef.child(sendId).child(receiverId).push().key
                ?: throw Exception("Cannot generate message key")
            val messageData = mapOf(
                "timestamp" to ServerValue.TIMESTAMP,
                "senderId" to sendId,
                "messageText" to messageText
            )

            messagesRef.child(sendId).child(receiverId).child(messageKey).setValue(messageData).await()
            messagesRef.child(receiverId).child(sendId).child(messageKey).setValue(messageData).await()

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to send message: ${e.message}"))
        }
    }

    override fun fetchMessages(friendId: String): Flow<Resource<List<GetMessage>>> = callbackFlow {
        val currentUser = firebaseAuth.currentUser
        if (currentUser == null) {
            trySend(Resource.Error("No authenticated user found"))
            close()
            return@callbackFlow
        }

        val userId = currentUser.uid
        val messagesRef = firebaseDatabase.getReference("Messages").child(userId).child(friendId)
        val messagesList = mutableListOf<GetMessage>()

        val messageListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(GetMessage::class.java)
                message?.let {
                    messagesList.add(it)
                    trySend(Resource.Success(messagesList.toList()))  // Sending the updated list of messages
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // Optional: Update message in messagesList and resend the list if necessary
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // Optional: Handle removal of message in messagesList and resend the list if necessary
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Optional: Handle moving of message in messagesList and resend the list if necessary
            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySend(Resource.Error("Failed to fetch messages: ${databaseError.message}"))
            }
        }

        messagesRef.addChildEventListener(messageListener)
        awaitClose { messagesRef.removeEventListener(messageListener) }
    }
}