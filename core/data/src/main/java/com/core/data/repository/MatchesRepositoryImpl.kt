package com.core.data.repository

import com.core.common.mapper.asResource
import com.core.common.resource.HandleRetrofitResponse
import com.core.common.resource.Resource
import com.core.data.mapper.matches.toDomainModel
import com.core.data.service.MatchesService
import com.core.domain.model.matches.live.GetMatchDetails
import com.core.domain.model.matches.live.GetMatchWrapper
import com.core.domain.model.matches.live.GetTeamWrapper
import com.core.domain.model.matches.upcoming.GetUpcomingMatch
import com.core.domain.repository.MatchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val service: MatchesService,
    private val responseHandler: HandleRetrofitResponse
) : MatchesRepository {
    override suspend fun getRunningMatches(): Flow<Resource<List<GetMatchWrapper>>> {
        return responseHandler.apiCall {
            service.getLiveMatches()
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }

    override suspend fun getMatchById(matchId: Int): Flow<Resource<GetMatchDetails>> {
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

    override suspend fun getUpcomingMatches(): Flow<Resource<List<GetUpcomingMatch>>> {
        return responseHandler.apiCall {
            service.getUpcomingMatches()
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }
}