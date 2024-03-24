package com.core.common.resource.auth

import com.core.common.resource.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class HandleCurrentUserResponse @Inject constructor(private val databaseReference: DatabaseReference) {
    fun getUserDetails(userId: String): Flow<Resource<Map<String, String>>> = callbackFlow {
        val userRef = databaseReference.child("Users").child(userId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val typeIndicator = object : GenericTypeIndicator<Map<String, String>>() {}
                val userDetails = snapshot.getValue(typeIndicator) ?: emptyMap()

                if (userDetails.isNotEmpty()) {
                    trySend(Resource.Success(userDetails)).isSuccess
                } else {
                    trySend(Resource.Error("User details not found")).isSuccess
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error("Failed to fetch user details: ${error.message}")).isSuccess
            }
        }

        userRef.addValueEventListener(listener)
        awaitClose { userRef.removeEventListener(listener) }
    }
}