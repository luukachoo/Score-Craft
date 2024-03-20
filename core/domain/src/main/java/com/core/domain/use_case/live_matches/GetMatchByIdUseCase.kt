package com.core.domain.use_case.live_matches

import com.core.domain.repository.match.MatchesRepository
import javax.inject.Inject

class GetMatchByIdUseCase @Inject constructor(private val repository: MatchesRepository) {
    suspend operator fun invoke(matchId: Int) =
        repository.getMatchById(matchId)
}