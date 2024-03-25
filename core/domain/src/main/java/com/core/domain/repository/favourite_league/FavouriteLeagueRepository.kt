package com.core.domain.repository.favourite_league

import com.core.common.resource.Resource
import kotlinx.coroutines.flow.Flow

interface FavouriteLeagueRepository {
    suspend fun fetchFavouriteLeagues(): Flow<Resource<Set<String>>>
}