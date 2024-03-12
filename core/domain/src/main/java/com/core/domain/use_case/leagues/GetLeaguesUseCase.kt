package com.core.domain.use_case.leagues

import com.core.domain.repository.league.LeagueRepository
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(private val leagueRepository: LeagueRepository) {
    suspend operator fun invoke(page: Int, limit: Int) =
        leagueRepository.getLeagues(page, limit)
}