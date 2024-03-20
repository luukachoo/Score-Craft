package com.core.data.service

import com.core.data.model.league.LeagueDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LeaguesService {
    @GET("leagues")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getLeagues(
        @Query("page") size: Int,
        @Query("per_page") number: Int
    ): Response<List<LeagueDto>>
}