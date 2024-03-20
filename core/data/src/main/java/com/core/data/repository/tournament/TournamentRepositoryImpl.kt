package com.core.data.repository.tournament

import com.core.common.mapper.asResource
import com.core.common.resource.Resource
import com.core.common.resource.retrofit.HandleRetrofitResponse
import com.core.data.mapper.league.toDomainModel
import com.core.data.service.TournamentsService
import com.core.domain.model.league.GetTournament
import com.core.domain.repository.tournament.TournamentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TournamentRepositoryImpl @Inject constructor(
    private val service: TournamentsService,
    private val responseHandler: HandleRetrofitResponse
) : TournamentRepository {
    override suspend fun getTournamentsBySerie(slug: String): Flow<Resource<List<GetTournament>>> {
        return responseHandler.apiCall {
            service.getTournamentsBySlug(slug = slug)
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }

    override suspend fun getTournamentDetails(slug: String): Flow<Resource<GetTournament>> {
        return responseHandler.apiCall {
            service.getTournamentDetails(slug = slug)
        }.asResource {
            it.toDomainModel()
        }
    }
}