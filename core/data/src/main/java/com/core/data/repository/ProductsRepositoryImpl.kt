package com.core.data.repository

import com.core.common.mapper.asResource
import com.core.common.resource.HandleRetrofitResponse
import com.core.common.resource.Resource
import com.core.data.mapper.toDomainModel
import com.core.data.service.ProductsService
import com.core.domain.model.GetProduct
import com.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val service: ProductsService,
    private val responseHandler: HandleRetrofitResponse
) : ProductRepository {
    override suspend fun getProducts(): Flow<Resource<List<GetProduct>>> {
        return responseHandler.apiCall {
            service.getProducts()
        }.asResource {
            it.map { productDto ->
                productDto.toDomainModel()
            }
        }
    }

}