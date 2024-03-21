package com.core.domain.use_case.friend

import com.core.domain.repository.friends.FriendRepository
import javax.inject.Inject

class GetFetchFriendRequestsUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke() = friendRepository.fetchFriendRequests()
}