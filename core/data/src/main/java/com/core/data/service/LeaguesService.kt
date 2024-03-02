package com.core.data.service

import com.core.data.model.LeagueDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface LeaguesService {
    @GET("leagues")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getLeagues(): Response<List<LeagueDto>>
}