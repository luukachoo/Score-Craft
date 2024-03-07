package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.GetMatchDetails
import com.core.domain.model.GetMatchWrapper
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    suspend fun getRunningMatches(): Flow<Resource<List<GetMatchWrapper>>>
    suspend fun getMatchById(matchId: Int): Flow<Resource<GetMatchDetails>>
    suspend fun getMatchOpponents(matchId: Int): Flow<Resource<Unit>>
}

