package com.core.data.service

import com.core.data.model.product.ProductDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): Response<List<ProductDto>>
}