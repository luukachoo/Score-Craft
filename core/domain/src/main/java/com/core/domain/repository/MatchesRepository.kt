package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.live_matches.GetMatchDetails
import com.core.domain.model.live_matches.GetMatchWrapper
import com.core.domain.model.live_matches.GetTeamWrapper
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    suspend fun getRunningMatches(): Flow<Resource<List<GetMatchWrapper>>>
    suspend fun getMatchById(matchId: Int): Flow<Resource<GetMatchDetails>>
    suspend fun getMatchOpponents(matchId: Int): Flow<Resource<GetTeamWrapper>>
}

