package com.core.data.repository

import com.core.common.mapper.asResource
import com.core.common.resource.HandleRetrofitResponse
import com.core.common.resource.Resource
import com.core.data.mapper.toDomainModel
import com.core.data.service.MatchesService
import com.core.domain.model.GetMatchDetails
import com.core.domain.model.GetMatchWrapper
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

//    override suspend fun getMatchOpponents(matchId: Int): Flow<Resource<Unit>> {
//        return responseHandler.apiCall {
//            service.getMatchOpponents(matchId)
//        }.asResource {
//
//        }
//    }
}