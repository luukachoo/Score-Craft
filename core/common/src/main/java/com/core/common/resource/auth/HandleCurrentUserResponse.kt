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
                val userDetails = snapshot.getValue(object : GenericTypeIndicator<Map<String, String>>() {})
                if (!userDetails.isNullOrEmpty()) {
                    trySend(Resource.Success(userDetails)).isSuccess
                } else {
                    trySend(Resource.Error("User details not found or empty")).isSuccess
                }
            }

            override fun onCancelled(error: DatabaseError) {
                val errorMessage = when (error.code) {
                    DatabaseError.PERMISSION_DENIED -> "Permission denied to access user details."
                    DatabaseError.NETWORK_ERROR -> "Network error occurred while accessing user details."
                    DatabaseError.DATA_STALE -> "Data is outdated, please refresh."
                    DatabaseError.OPERATION_FAILED -> "Operation failed due to a server error."
                    else -> "Failed to fetch user details: ${error.message}"
                }
                trySend(Resource.Error(errorMessage)).isSuccess
            }
        }

        userRef.addValueEventListener(listener)
        awaitClose { userRef.removeEventListener(listener) }
    }
}
