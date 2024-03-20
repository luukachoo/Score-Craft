package com.core.data.service

import com.core.data.model.league.TournamentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface TournamentsService {
    @GET("series/{slug}/tournaments")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getTournamentsBySlug(@Path("slug") slug: String): Response<List<TournamentDto>>

    @GET("tournaments/{slug}")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getTournamentDetails(@Path("slug") slug: String): Response<TournamentDto>
}