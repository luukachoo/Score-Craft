package com.core.domain.repository.tournament

import com.core.common.resource.Resource
import com.core.domain.model.league.GetTeamStanding
import com.core.domain.model.league.GetTournament
import com.core.domain.model.league.GetTournamentMatch
import kotlinx.coroutines.flow.Flow

interface TournamentRepository {
    suspend fun getTournamentsBySerie(slug: String): Flow<Resource<List<GetTournament>>>
    suspend fun getTournamentDetails(slug: String): Flow<Resource<GetTournament>>
    suspend fun getTeamStandingsBySlug(slug: String): Flow<Resource<List<GetTeamStanding>>>
    suspend fun getTournamentMatches(slug: String): Flow<Resource<List<GetTournamentMatch>>>
}