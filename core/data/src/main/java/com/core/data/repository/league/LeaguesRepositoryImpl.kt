package com.core.data.repository.league

import com.core.common.mapper.asResource
import com.core.common.resource.Resource
import com.core.common.resource.retrofit.HandleRetrofitResponse
import com.core.data.mapper.toDomainModel
import com.core.data.service.LeaguesService
import com.core.domain.model.GetLeague
import com.core.domain.model.GetSeries
import com.core.domain.repository.league.LeagueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val service: LeaguesService,
    private val responseHandler: HandleRetrofitResponse
) : LeagueRepository {
    override suspend fun getLeagues(page: Int, limit: Int): Flow<Resource<List<GetLeague>>> {
        return responseHandler.apiCall {
            service.getLeagues(page, limit)
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }
}