package com.example.marketmingle.di

import com.core.data.repository.LeaguesRepositoryImpl
import com.core.data.repository.MatchesRepositoryImpl
import com.core.domain.repository.LeagueRepository
import com.core.domain.repository.MatchesRepository
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
    fun bindCategoryRepository(categoriseRepositoryImpl: LeaguesRepositoryImpl): LeagueRepository

    @Binds
    @Singleton
    fun bindProductRepository(productRepositoryImpl: MatchesRepositoryImpl): MatchesRepository
}