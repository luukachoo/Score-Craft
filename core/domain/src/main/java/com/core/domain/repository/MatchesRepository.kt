package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.match.live.GetLiveMatchDetails
import com.core.domain.model.match.live.GetLiveMatchWrapper
import com.core.domain.model.match.GetTeamWrapper
import com.core.domain.model.match.GetMatch
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    suspend fun getRunningMatches(): Flow<Resource<List<GetLiveMatchWrapper>>>
    suspend fun getMatchById(matchId: Int): Flow<Resource<GetLiveMatchDetails>>
    suspend fun getMatchOpponents(matchId: Int): Flow<Resource<GetTeamWrapper>>
    suspend fun getUpcomingMatches(): Flow<Resource<List<GetMatch>>>
    suspend fun getPastMatches(): Flow<Resource<List<GetMatch>>>
}

