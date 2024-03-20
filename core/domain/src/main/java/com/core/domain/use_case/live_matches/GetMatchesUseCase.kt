package com.core.domain.use_case.live_matches

import com.core.domain.repository.match.MatchesRepository
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(private val repository: MatchesRepository) {
    suspend operator fun invoke() =
        repository.getRunningMatches()
}