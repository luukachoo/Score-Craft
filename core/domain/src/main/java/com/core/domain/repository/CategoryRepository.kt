package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.GetCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Resource<List<GetCategory>>>
}