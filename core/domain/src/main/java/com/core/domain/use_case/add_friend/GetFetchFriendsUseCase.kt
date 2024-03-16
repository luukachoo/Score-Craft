package com.core.domain.use_case.add_friend

import com.core.common.resource.Resource
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.add_friend.AddFriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFetchFriendsUseCase @Inject constructor(
    private val addFriendRepository: AddFriendRepository
){
    suspend operator fun invoke(): Flow<Resource<List<GetUsers>>> = addFriendRepository.fetchFriends()
}