package com.core.domain.repository

import com.core.common.resource.Resource
import com.core.domain.model.GetProduct
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Flow<Resource<List<GetProduct>>>
}