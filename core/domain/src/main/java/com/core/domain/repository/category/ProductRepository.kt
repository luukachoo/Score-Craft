package com.core.domain.repository.category

import com.core.common.resource.Resource
import com.core.domain.model.category.GetProduct
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<Resource<List<GetProduct>>>
}