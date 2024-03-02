package com.core.data.service

import retrofit2.Response
import retrofit2.http.GET

interface LiveMatchesService {
    @GET("lives")
    suspend fun getLiveMatches(): Response<List<LiveMatchDto>>
}