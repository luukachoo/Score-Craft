package com.core.domain.use_case.live_matches

import com.core.domain.repository.match.MatchesRepository
import javax.inject.Inject

class GetPastMatchesUseCase @Inject constructor(private val matchesRepository: MatchesRepository) {
    suspend operator fun invoke() =
        matchesRepository.getPastMatches()
}