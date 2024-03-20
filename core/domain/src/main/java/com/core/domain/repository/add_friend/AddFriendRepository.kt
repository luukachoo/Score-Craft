package com.core.domain.repository.add_friend

import com.core.common.resource.Resource
import com.core.domain.model.auth.GetUsers
import kotlinx.coroutines.flow.Flow

interface AddFriendRepository {
    suspend fun addFriend(userName: String) : Flow<Resource<String>>
    suspend fun fetchFriends() : Flow<Resource<List<GetUsers>>>
    suspend fun fetchFriendRequests() : Flow<Resource<List<GetUsers>>>
    suspend fun getFCMTokenForUser(userName: String) : Flow<Resource<String>>
    suspend fun acceptFriendRequest(friendId: String) : Flow<Resource<Unit>>
    suspend fun rejectFriendRequest(friendId: String) : Flow<Resource<Unit>>
}