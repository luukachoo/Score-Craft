package com.core.domain.use_case.leagues

import com.core.domain.model.league.GetLeague
import com.core.domain.repository.league.LeagueRepository
import javax.inject.Inject

class GetSaveFavouriteLeagues @Inject constructor(
    private val leagueRepository: LeagueRepository
) {
    suspend operator fun invoke(league: GetLeague) =
        leagueRepository.saveFavouriteLeagues(league)
}