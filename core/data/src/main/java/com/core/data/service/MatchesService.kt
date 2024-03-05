package com.core.data.service

import com.core.data.model.MatchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MatchesService {
    @GET("lives")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getLiveMatches(): Response<List<MatchDto>>
}