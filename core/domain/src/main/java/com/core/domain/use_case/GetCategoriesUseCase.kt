package com.core.domain.use_case

import com.core.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke() =
        categoryRepository.getCategories()
}