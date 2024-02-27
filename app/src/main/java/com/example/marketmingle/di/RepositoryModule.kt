package com.example.marketmingle.di

import com.core.data.repository.CategoriesRepositoryImpl
import com.core.data.repository.ProductsRepositoryImpl
import com.core.domain.repository.CategoryRepository
import com.core.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindCategoryRepository(categoriseRepositoryImpl: CategoriesRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    fun bindProductRepository(productRepositoryImpl: ProductsRepositoryImpl): ProductRepository
}