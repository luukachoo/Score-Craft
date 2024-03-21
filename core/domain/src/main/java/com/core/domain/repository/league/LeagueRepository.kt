package com.core.domain.repository.league

import com.core.common.resource.Resource
import com.core.domain.model.league.GetLeague
import kotlinx.coroutines.flow.Flow

interface LeagueRepository {
    suspend fun getLeagues(page: Int, limit: Int): Flow<Resource<List<GetLeague>>>
    suspend fun saveFavouriteLeagues(league: GetLeague): Flow<Resource<String>>
    suspend fun fetchFavouriteLeagues(): Flow<Resource<List<GetLeague>>>
}