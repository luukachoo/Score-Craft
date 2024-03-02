package com.core.domain.repository.category

import com.core.common.resource.Resource
import com.core.domain.model.category.GetCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Resource<List<GetCategory>>>
}