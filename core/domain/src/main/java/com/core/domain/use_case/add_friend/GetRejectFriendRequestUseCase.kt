package com.core.domain.use_case.add_friend

import com.core.domain.repository.add_friend.AddFriendRepository
import javax.inject.Inject

class GetRejectFriendRequestUseCase @Inject constructor(
    private val addFriendRepository: AddFriendRepository
) {
    suspend operator fun invoke(friendId: String) = addFriendRepository.rejectFriendRequest(friendId)
}