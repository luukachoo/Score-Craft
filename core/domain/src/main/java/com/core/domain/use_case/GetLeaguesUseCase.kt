package com.core.domain.use_case

import com.core.domain.repository.LeagueRepository
import javax.inject.Inject

class GetLeaguesUseCase @Inject constructor(private val leagueRepository: LeagueRepository) {
    suspend operator fun invoke() =
        leagueRepository.getLeagues()
}