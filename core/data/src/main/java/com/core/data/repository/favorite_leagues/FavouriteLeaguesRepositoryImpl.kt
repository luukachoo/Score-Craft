package com.core.data.repository.favorite_leagues

import com.core.common.resource.Resource
import com.core.domain.repository.favourite_league.FavouriteLeagueRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FavouriteLeaguesRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): FavouriteLeagueRepository {
    override suspend fun fetchFavouriteLeagues(): Flow<Resource<Set<String>>> = flow {
        emit(Resource.Loading(true))
        try {
            val userId = firebaseAuth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            val databaseReference = FirebaseDatabase.getInstance().getReference("UserLeagues").child(userId)
            val snapshot = databaseReference.get().await()
            val favourites = snapshot.children
                .mapNotNull { it.key }
                .toSet()

            emit(Resource.Success(favourites))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to fetch favourite leagues: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}