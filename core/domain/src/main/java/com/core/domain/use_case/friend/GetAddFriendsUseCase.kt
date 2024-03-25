package com.core.domain.use_case.friend

import com.core.domain.repository.friends.FriendRepository
import javax.inject.Inject

class GetAddFriendsUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke(userName: String) = friendRepository.addFriend(userName)
}