package com.core.data.repository.add_friends

import com.core.common.resource.Resource
import com.core.data.mapper.auth.toDomain
import com.core.data.model.auth.UserDto
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.add_friend.AddFriendRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddFriendsRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : AddFriendRepository {
    override suspend fun addFriend(userName: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))
        try {
            val userId = firebaseAuth.currentUser?.uid
                ?: throw IllegalStateException("User not logged in")

            val usersRef = FirebaseDatabase.getInstance().getReference("Users")
            val query = usersRef.orderByChild("userName").equalTo(userName)
            val snapshot = query.get().await()

            if (snapshot.exists()) {
                val friendId = snapshot.children.first().key

                val userFriendsRef =
                    FirebaseDatabase.getInstance().getReference("UserFriends").child(userId)
                userFriendsRef.child(friendId!!).setValue(false).await()

                val friendUserFriendsRef =
                    FirebaseDatabase.getInstance().getReference("UserFriends").child(friendId)
                friendUserFriendsRef.child(userId).setValue(false).await()

                emit(Resource.Success("Friend added successfully"))
            } else {
                emit(Resource.Error("User with username $userName not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Failed to add friend: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getFCMTokenForUser(userName: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))
        try {
            val usersRef = firebaseDatabase.getReference("Users")
            val query = usersRef.orderByChild("userName").equalTo(userName)
            val snapshot = query.get().await()

            if (snapshot.exists()) {
                val userDto = snapshot.children.first().getValue(UserDto::class.java)
                val fcmToken =
                    userDto?.fcmToken ?: throw IllegalStateException("FCM token not found")
                emit(Resource.Success(fcmToken))
            } else {
                emit(Resource.Error("User not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching FCM token: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun fetchFriends(): Flow<Resource<List<GetUsers>>> = callbackFlow {
        val userId =
            firebaseAuth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
        val userFriendsRef = firebaseDatabase.getReference("UserFriends").child(userId)

        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val friendsDetails = mutableListOf<UserDto>()
                dataSnapshot.children.forEach { child ->
                    firebaseDatabase.getReference("Users").child(child.key!!).get()
                        .addOnSuccessListener { userSnapshot ->
                            val friendDto = userSnapshot.getValue(UserDto::class.java)
                            friendDto?.let {
                                friendsDetails.add(it)
                                trySend(Resource.Success(friendsDetails.map { dto -> dto.toDomain() }))
                            }
                        }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySend(Resource.Error("Failed to fetch friends: ${databaseError.message}"))
            }
        }

        userFriendsRef.addValueEventListener(eventListener)
        awaitClose { userFriendsRef.removeEventListener(eventListener) }
    }
}