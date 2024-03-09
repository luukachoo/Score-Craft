package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.matches.live.GetMatchDetails
import com.core.domain.model.matches.live.GetMatchWrapper
import com.core.domain.model.matches.live.GetTeamWrapper
import com.core.domain.model.matches.upcoming.GetUpcomingMatch
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    suspend fun getRunningMatches(): Flow<Resource<List<GetMatchWrapper>>>
    suspend fun getMatchById(matchId: Int): Flow<Resource<GetMatchDetails>>
    suspend fun getMatchOpponents(matchId: Int): Flow<Resource<GetTeamWrapper>>
    suspend fun getUpcomingMatches(): Flow<Resource<List<GetUpcomingMatch>>>
}

