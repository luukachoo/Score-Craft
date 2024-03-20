package com.core.domain.repository.series

import com.core.common.resource.Resource
import com.core.domain.model.league.GetSeries
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeriesBySlug(slug: String): Flow<Resource<List<GetSeries>>>
}