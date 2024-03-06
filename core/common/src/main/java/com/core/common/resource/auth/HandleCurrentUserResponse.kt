package com.core.common.resource.auth

import android.util.Log.d
import com.core.common.resource.Resource
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HandleCurrentUserResponse @Inject constructor(private val databaseReference: DatabaseReference) {
    fun getUserDetails(userId: String): Flow<Resource<Map<String, String>>> = flow {
        emit(Resource.Loading(true))
        try {
            val snapshot = databaseReference.child("Users").child(userId).get().await()
            d("FirebaseDebug", "Snapshot: ${snapshot.value}")
            val userDetails = snapshot.value as? Map<String, String>
            if (userDetails != null && userDetails.keys.containsAll(
                    listOf(
                        "email",
                        "firstName",
                        "lastName",
                        "userName"
                    )
                )
            ) {
                emit(Resource.Success(userDetails))
            } else {
                emit(Resource.Error("User details not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while retrieving user details: ${e.message}"))
        }
        emit(Resource.Loading(false))
    }
}
