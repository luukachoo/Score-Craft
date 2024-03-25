package com.core.data.repository.series

import com.core.common.mapper.asResource
import com.core.common.resource.Resource
import com.core.common.resource.retrofit.HandleRetrofitResponse
import com.core.data.mapper.league.toDomainModel
import com.core.data.service.serie.SeriesService
import com.core.domain.model.league.GetSeries
import com.core.domain.repository.series.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val service: SeriesService,
    private val responseHandler: HandleRetrofitResponse
) : SeriesRepository {
    override suspend fun getSeriesBySlug(slug: String): Flow<Resource<List<GetSeries>>> {
        return responseHandler.apiCall {
            service.getSeriesBySlug(slug)
        }.asResource { it ->
            it.map {
                it.toDomainModel()
            }
        }
    }
}