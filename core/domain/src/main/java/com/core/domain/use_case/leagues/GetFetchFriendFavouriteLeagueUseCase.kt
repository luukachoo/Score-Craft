package com.core.domain.use_case.leagues

import com.core.domain.repository.league.LeagueRepository
import javax.inject.Inject

class GetFetchFriendFavouriteLeagueUseCase @Inject constructor(
    private val leagueRepository: LeagueRepository
) {
    suspend operator fun invoke(friendId: String) = leagueRepository.fetchFriendFavouriteLeague(friendId)
}