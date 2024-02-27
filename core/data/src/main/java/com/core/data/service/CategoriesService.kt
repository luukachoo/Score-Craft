package com.core.data.service

import com.core.data.model.CategoryDto
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesService {
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryDto>>
}