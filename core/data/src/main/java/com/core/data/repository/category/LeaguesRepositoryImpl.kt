package com.core.data.repository.category

import com.core.common.mapper.asResource
import com.core.common.resource.HandleRetrofitResponse
import com.core.common.resource.Resource
import com.core.data.mapper.toDomainModel
import com.core.data.service.LeaguesService
import com.core.domain.model.GetLeague
import com.core.domain.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val service: LeaguesService,
    private val responseHandler: HandleRetrofitResponse
) : LeagueRepository {
    override suspend fun getLeagues(): Flow<Resource<List<GetLeague>>> {
        return responseHandler.apiCall {
            service.getLeagues()
        }.asResource {
            it.map { leagueDto ->
                leagueDto.toDomainModel()
            }
        }
    }
}