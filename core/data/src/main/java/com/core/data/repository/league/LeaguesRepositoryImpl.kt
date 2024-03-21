package com.core.data.repository.league

import com.core.common.mapper.asResource
import com.core.common.resource.Resource
import com.core.common.resource.retrofit.HandleRetrofitResponse
import com.core.data.mapper.league.toDomainModel
import com.core.data.service.LeaguesService
import com.core.domain.model.league.GetLeague
import com.core.domain.repository.league.LeagueRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val service: LeaguesService,
    private val responseHandler: HandleRetrofitResponse,
    private val firebaseAuth: FirebaseAuth,
) : LeagueRepository {
    override suspend fun getLeagues(page: Int, limit: Int): Flow<Resource<List<GetLeague>>> {
        return responseHandler.apiCall {
            service.getLeagues(page, limit)
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }

    override suspend fun saveFavouriteLeagues(league: GetLeague): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))
        try {
            val userId = firebaseAuth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            val databaseReference = FirebaseDatabase.getInstance().getReference("UserLeagues").child(userId)
            val snapshot = databaseReference.child(league.slug).get().await()

            val message: String = if (snapshot.exists()) {
                databaseReference.child(league.slug).removeValue().await()
                "Removed from favourites"
            } else {
                databaseReference.child(league.slug).setValue(league).await()
                "Added to favourites"
            }

            emit(Resource.Success(message))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to update favourite league: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun fetchFavouriteLeagues(): Flow<Resource<List<GetLeague>>> = flow {
        emit(Resource.Loading(true))
        try {
            val userId = firebaseAuth.currentUser?.uid ?: throw IllegalStateException("User not logged in")
            val databaseReference = FirebaseDatabase.getInstance().getReference("UserLeagues").child(userId)
            val snapshot = databaseReference.get().await()

            val favourites = snapshot.children.mapNotNull { childSnapshot ->
                childSnapshot.getValue(GetLeague::class.java)
            }

            emit(Resource.Success(favourites))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to fetch favourite leagues: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}