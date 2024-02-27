package com.core.data.repository

import com.core.common.mapper.asResource
import com.core.common.resource.HandleRetrofitResponse
import com.core.common.resource.Resource
import com.core.data.mapper.toDomainModel
import com.core.data.service.CategoriesService
import com.core.domain.model.GetCategory
import com.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val service: CategoriesService,
    private val responseHandler: HandleRetrofitResponse
) : CategoryRepository {
    override suspend fun getCategories(): Flow<Resource<List<GetCategory>>> {
        return responseHandler.apiCall {
            service.getCategories()
        }.asResource {
            it.map { categoryDto ->
                categoryDto.toDomainModel()
            }
        }
    }
}