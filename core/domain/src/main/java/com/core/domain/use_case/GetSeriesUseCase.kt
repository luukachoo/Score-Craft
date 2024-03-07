package com.core.domain.use_case

import com.core.domain.repository.LeagueRepository
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val leagueRepository: LeagueRepository) {
    suspend operator fun invoke(slug: String) =
        leagueRepository.getSeriesBySlug(slug = slug)
}