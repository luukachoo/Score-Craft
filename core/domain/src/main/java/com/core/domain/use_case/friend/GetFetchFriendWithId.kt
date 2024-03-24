package com.core.domain.use_case.friend

import com.core.domain.repository.friends.FriendRepository
import javax.inject.Inject

class GetFetchFriendWithId @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke(friendId: String) = friendRepository.fetchFriendWithId(friendId)
}