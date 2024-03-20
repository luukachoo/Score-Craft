package com.core.domain.use_case.tournament

import com.core.domain.repository.tournament.TournamentRepository
import javax.inject.Inject

class GetTournamentDetailsUseCase @Inject constructor(private val repository: TournamentRepository) {
    suspend operator fun invoke(slug: String) =
        repository.getTournamentDetails(slug)
}