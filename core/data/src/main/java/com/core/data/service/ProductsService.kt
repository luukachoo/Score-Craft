package com.core.data.service

import com.core.data.model.product.ProductDto
import retrofit2.Response

interface ProductsService {
    suspend fun getProducts(): Response<List<ProductDto>>
}