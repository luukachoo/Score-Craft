package com.core.data.service

import com.core.data.model.league.SeriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface SeriesService {
    @GET("leagues/{slug}/series")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getSeriesBySlug(@Path("slug") slug: String): Response<List<SeriesDto>>
}