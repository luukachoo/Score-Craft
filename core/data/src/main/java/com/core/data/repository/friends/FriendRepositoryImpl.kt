package com.core.data.repository.friends

import com.core.common.resource.Resource
import com.core.data.mapper.auth.toDomain
import com.core.data.model.auth.UserDto
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.friends.FriendRepository
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

class FriendRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : FriendRepository {
    override suspend fun addFriend(userName: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))
        try {
            val currentUserId = firebaseAuth.currentUser?.uid
                ?: throw IllegalStateException("User not logged in")

            val usersRef = FirebaseDatabase.getInstance().getReference("Users")
            val query = usersRef.orderByChild("userName").equalTo(userName)
            val snapshot = query.get().await()

            if (snapshot.exists()) {
                val friendId = snapshot.children.first().key

                if (friendId == currentUserId) {
                    emit(Resource.Error("You cannot send a friend request to yourself"))
                    return@flow
                }

                val friendRequestsRef =
                    FirebaseDatabase.getInstance().getReference("friendRequests")

                friendRequestsRef.child(friendId!!).child(currentUserId).setValue(true).await()

                emit(Resource.Success("Friend request sent successfully"))
            } else {
                emit(Resource.Error("User with username $userName not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Failed to add friend: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun acceptFriendRequest(friendId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading(true))
        try {
            val userId = firebaseAuth.currentUser?.uid
                ?: throw IllegalStateException("User not logged in")

            val friendRequestsRef =
                firebaseDatabase.getReference("friendRequests").child(userId).child(friendId)

            friendRequestsRef.removeValue().await()

            val userFriendsRef = firebaseDatabase.getReference("UserFriends")
            val userRef = userFriendsRef.child(userId).child(friendId)
            val friendRef = userFriendsRef.child(friendId).child(userId)

            val userTask = userRef.setValue(true)
            val friendTask = friendRef.setValue(true)

            userTask.await()
            friendTask.await()

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to accept friend request: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun rejectFriendRequest(friendId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading(true))
        try {
            val userId = firebaseAuth.currentUser?.uid
                ?: throw IllegalStateException("User not logged in")

            val friendRequestsRef =
                firebaseDatabase.getReference("friendRequests").child(userId).child(friendId)

            friendRequestsRef.removeValue().await()

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to reject friend request: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun fetchFriendWithId(friendId: String): Flow<Resource<GetUsers>> = flow {
        emit(Resource.Loading(true))
        try {
            val userSnapshot = firebaseDatabase.getReference("Users").child(friendId).get().await()
            val userDto = userSnapshot.getValue(UserDto::class.java)
            if (userDto != null) {
                val friendDetails = userDto.toDomain()
                emit(Resource.Success(friendDetails))
            } else {
                emit(Resource.Error("Friend not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Failed to fetch friend details: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun removeFriend(friendId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading(true))
        try {
            val currentUserId = firebaseAuth.currentUser?.uid
                ?: throw IllegalStateException("User not logged in")

            val currentUserFriendRef = firebaseDatabase.getReference("UserFriends")
                .child(currentUserId)
                .child(friendId)

            val friendFriendRef = firebaseDatabase.getReference("UserFriends")
                .child(friendId)
                .child(currentUserId)

            val task1 = currentUserFriendRef.removeValue()
            val task2 = friendFriendRef.removeValue()

            task1.await()
            task2.await()

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to remove friend: ${e.message}"))
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
        val currentUserId =
            firebaseAuth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
        val userFriendsRef = firebaseDatabase.getReference("UserFriends").child(currentUserId)

        val friendsDetails = mutableMapOf<String, GetUsers>()

        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { child ->
                    val friendId = child.key!!
                    val isFriend = child.getValue(Boolean::class.java) ?: false

                    if (isFriend) {
                        firebaseDatabase.getReference("UserFriends").child(friendId)
                            .child(currentUserId)
                            .addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(friendSnapshot: DataSnapshot) {
                                    val friendStatus =
                                        friendSnapshot.getValue(Boolean::class.java) ?: false
                                    if (friendStatus) {
                                        firebaseDatabase.getReference("Users").child(friendId).get()
                                            .addOnSuccessListener { userSnapshot ->
                                                val userDto =
                                                    userSnapshot.getValue(UserDto::class.java)
                                                userDto?.let { dto ->
                                                    synchronized(friendsDetails) {
                                                        dto.userId = friendId
                                                        friendsDetails[friendId] = dto.toDomain()
                                                        trySend(Resource.Success(friendsDetails.values.toList()))
                                                    }
                                                }
                                            }
                                    } else {
                                        synchronized(friendsDetails) {
                                            friendsDetails.remove(friendId)
                                            trySend(Resource.Success(friendsDetails.values.toList()))
                                        }
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // Handle potential cancellation here
                                }
                            })
                    } else {
                        synchronized(friendsDetails) {
                            friendsDetails.remove(friendId)
                            trySend(Resource.Success(friendsDetails.values.toList()))
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

    override suspend fun fetchFriendRequests(): Flow<Resource<List<GetUsers>>> = callbackFlow {
        val currentUserId =
            firebaseAuth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
        val friendRequestsRef = firebaseDatabase.getReference("friendRequests").child(currentUserId)

        val friendRequestsDetails = mutableMapOf<String, GetUsers>()

        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                synchronized(friendRequestsDetails) {
                    friendRequestsDetails.clear()

                    dataSnapshot.children.forEach { friendRequestSnapshot ->
                        val senderId = friendRequestSnapshot.key!!
                        if (friendRequestSnapshot.getValue(Boolean::class.java) == true) {
                            firebaseDatabase.getReference("Users").child(senderId).get()
                                .addOnSuccessListener { userSnapshot ->
                                    val userDto = userSnapshot.getValue(UserDto::class.java)
                                    userDto?.let { dto ->
                                        dto.userId = senderId
                                        friendRequestsDetails[senderId] = dto.toDomain()
                                        trySend(Resource.Success(friendRequestsDetails.values.toList()))
                                    }
                                }
                        }
                    }
                    trySend(Resource.Success(friendRequestsDetails.values.toList()))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySend(Resource.Error("Failed to fetch friend requests: ${databaseError.message}"))
            }
        }

        friendRequestsRef.addValueEventListener(eventListener)
        awaitClose { friendRequestsRef.removeEventListener(eventListener) }
    }
}