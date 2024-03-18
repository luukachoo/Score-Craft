package com.core.data.repository.match

import com.core.common.mapper.asResource
import com.core.common.resource.Resource
import com.core.common.resource.retrofit.HandleRetrofitResponse
import com.core.data.mapper.match.toDomainModel
import com.core.data.service.MatchesService
import com.core.domain.model.match.live.GetLiveMatchDetails
import com.core.domain.model.match.live.GetLiveMatchWrapper
import com.core.domain.model.match.GetTeamWrapper
import com.core.domain.model.match.GetMatch
import com.core.domain.repository.MatchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val service: MatchesService,
    private val responseHandler: HandleRetrofitResponse
) : MatchesRepository {
    override suspend fun getRunningMatches(): Flow<Resource<List<GetLiveMatchWrapper>>> {
        return responseHandler.apiCall {
            service.getLiveMatches()
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }

    override suspend fun getMatchById(matchId: Int): Flow<Resource<GetLiveMatchDetails>> {
        return responseHandler.apiCall {
            service.getMatchById(matchId)
        }.asResource {
            it.toDomainModel()
        }
    }

    override suspend fun getMatchOpponents(matchId: Int): Flow<Resource<GetTeamWrapper>> {
        return responseHandler.apiCall {
            service.getMatchOpponents(matchId)
        }.asResource {
            it.toDomainModel()
        }
    }

    override suspend fun getUpcomingMatches(): Flow<Resource<List<GetMatch>>> {
        return responseHandler.apiCall {
            service.getUpcomingMatches(pageNumber = 1, size = 10)
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }

    override suspend fun getPastMatches(): Flow<Resource<List<GetMatch>>> {
        return responseHandler.apiCall {
            service.getPastMatches(pageNumber = 2, size = 15)
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }
}