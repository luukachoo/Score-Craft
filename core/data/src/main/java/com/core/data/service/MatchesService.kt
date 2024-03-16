package com.core.data.service

import com.core.data.model.matches.live.MatchDetailsDto
import com.core.data.model.matches.live.MatchWrapperDto
import com.core.data.model.matches.live.TeamWrapperDto
import com.core.data.model.matches.past.PastMatchDto
import com.core.data.model.matches.upcoming.UpcomingMatchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchesService {
    @GET("lives")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getLiveMatches(): Response<List<MatchWrapperDto>>

    @GET("matches/{matchId}")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getMatchById(@Path("matchId") matchId: Int): Response<MatchDetailsDto>

    @GET("matches/{matchId}/opponents")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getMatchOpponents(@Path("matchId") matchId: Int): Response<TeamWrapperDto>

    @GET("matches/upcoming")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getUpcomingMatches(
        @Query("page") pageNumber: Int,
        @Query("per_page") size: Int
    ): Response<List<UpcomingMatchDto>>

    @GET("matches/past")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getPastMatches(
        @Query("page") pageNumber: Int,
        @Query("per_page") size: Int
    ): Response<List<PastMatchDto>>
}