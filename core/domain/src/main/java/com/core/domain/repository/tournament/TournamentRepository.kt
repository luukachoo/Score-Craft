package com.core.domain.repository.tournament

import com.core.common.resource.Resource
import com.core.domain.model.GetSeries
import kotlinx.coroutines.flow.Flow

interface TournamentRepository {
    suspend fun getSeriesBySlug(slug: String): Flow<Resource<List<GetSeries>>>
}