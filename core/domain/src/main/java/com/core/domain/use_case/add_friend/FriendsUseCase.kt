package com.core.domain.use_case.add_friend

import javax.inject.Inject

data class FriendsUseCase @Inject constructor(
    val getAddFriendsUseCase: GetAddFriendsUseCase,
    val getFetchFriendsUseCase: GetFetchFriendsUseCase,
    val getFCMTokenUseCase: GetFCMTokenUseCase
)
