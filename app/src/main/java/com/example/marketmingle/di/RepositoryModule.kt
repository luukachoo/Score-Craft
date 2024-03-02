package com.example.marketmingle.di

import com.core.data.repository.auth.AuthRepositoryImpl
import com.core.data.repository.category.CategoriesRepositoryImpl
import com.core.data.repository.product.ProductsRepositoryImpl
import com.core.domain.repository.auth.AuthRepository
import com.core.domain.repository.category.CategoryRepository
import com.core.domain.repository.category.ProductRepository
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

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}