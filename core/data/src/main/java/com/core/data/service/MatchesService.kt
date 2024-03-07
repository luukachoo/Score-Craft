package com.core.data.service

import com.core.data.model.MatchDetailsDto
import com.core.data.model.MatchWrapperDto
import com.core.data.model.OpponentWithTypeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MatchesService {
    @GET("lives")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getLiveMatches(): Response<List<MatchWrapperDto>>

    @GET("matches/{matchId}")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getMatchById(@Path("matchId") matchId: Int): Response<MatchDetailsDto>

    @GET("{matchId}/opponents")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getMatchOpponents(@Path("matchId") matchId: Int): Response<Unit>
}