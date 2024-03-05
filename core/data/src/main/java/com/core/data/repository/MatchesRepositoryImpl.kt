package com.core.data.repository

import com.core.common.mapper.asResource
import com.core.common.resource.HandleRetrofitResponse
import com.core.common.resource.Resource
import com.core.data.service.MatchesService
import com.core.data.toDomainModel
import com.core.domain.model.GetMatch
import com.core.domain.repository.MatchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val service: MatchesService,
    private val responseHandler: HandleRetrofitResponse
) : MatchesRepository {
    override suspend fun getRunningMatches(): Flow<Resource<List<GetMatch>>> {
        return responseHandler.apiCall {
            service.getLiveMatches()
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }
}