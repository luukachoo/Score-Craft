package com.core.data.repository

import com.core.common.mapper.asResource
import com.core.common.resource.HandleRetrofitResponse
import com.core.common.resource.Resource
import com.core.data.mapper.match_dto_mapper.toDomainModel
import com.core.data.service.MatchesService
import com.core.domain.repository.MatchesRepository
import com.core.domain.test_model.GetMatchListItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val service: MatchesService,
    private val responseHandler: HandleRetrofitResponse
) : MatchesRepository {
    override suspend fun getRunningMatches(): Flow<Resource<List<GetMatchListItem.GetMatch>>> {
        return responseHandler.apiCall {
            service.getLiveMatches()
        }.asResource { it.map { dto -> dto.toDomainModel() } }
    }
}