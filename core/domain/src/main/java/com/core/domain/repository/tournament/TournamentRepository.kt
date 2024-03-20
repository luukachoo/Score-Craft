package com.core.domain.repository.tournament

import com.core.common.resource.Resource
import com.core.domain.model.league.GetTournament
import kotlinx.coroutines.flow.Flow

interface TournamentRepository {
    suspend fun getTournamentsBySerie(slug: String): Flow<Resource<List<GetTournament>>>
    suspend fun getTournamentDetails(slug: String): Flow<Resource<GetTournament>>
}