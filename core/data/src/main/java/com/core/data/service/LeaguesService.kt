package com.core.data.service

import com.core.data.model.LeagueDto
import com.core.data.model.SeriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface LeaguesService {
    @GET("leagues")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getLeagues(
        @Query("Page[size]") size: Int = 30,
        @Query("page[number]") number: Int = 2
    ): Response<List<LeagueDto>>

    @GET("leagues/{slug}/series")
    @Headers("Authorization: 6x3HK0azW0zGDCZa7HTqv4de5n8EzkUuApWGYaIsBlKeiMnStOI")
    suspend fun getSeriesBySlug(@Path("slug") slug: String): Response<List<SeriesDto>>
}