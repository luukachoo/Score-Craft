package com.core.domain.use_case.favourite_leagues

import com.core.domain.repository.favourite_league.FavouriteLeagueRepository
import javax.inject.Inject

class GetFavouriteLeaguesUseCase @Inject constructor(
    private val favouriteLeagueRepository: FavouriteLeagueRepository
) {
    suspend operator fun invoke() = favouriteLeagueRepository.fetchFavouriteLeagues()
}