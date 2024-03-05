package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.GetMatch
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    suspend fun getRunningMatches(): Flow<Resource<List<GetMatch>>>
}