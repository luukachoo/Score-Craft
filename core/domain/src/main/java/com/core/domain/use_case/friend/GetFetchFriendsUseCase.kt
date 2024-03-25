package com.core.domain.use_case.friend

import com.core.common.resource.Resource
import com.core.domain.model.auth.GetUsers
import com.core.domain.repository.friends.FriendRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFetchFriendsUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<GetUsers>>> = friendRepository.fetchFriends()
}