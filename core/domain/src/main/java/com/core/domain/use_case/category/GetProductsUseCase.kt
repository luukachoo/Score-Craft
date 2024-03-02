package com.core.domain.use_case.category

import com.core.domain.repository.category.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke() =
        productRepository.getProducts()
}