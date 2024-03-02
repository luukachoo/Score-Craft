package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.GetLeague
import kotlinx.coroutines.flow.Flow

interface LeagueRepository {
    suspend fun getLeagues(): Flow<Resource<List<GetLeague>>>
}