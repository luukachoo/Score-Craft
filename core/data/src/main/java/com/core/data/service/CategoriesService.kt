package com.core.data.service

import com.core.data.model.category.CategoryDto
import retrofit2.Response

interface CategoriesService {
    suspend fun getCategories(): Response<List<CategoryDto>>
}