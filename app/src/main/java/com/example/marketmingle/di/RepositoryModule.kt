package com.example.marketmingle.di

import com.core.data.repository.favorite_leagues.FavouriteLeaguesRepositoryImpl
import com.core.data.repository.league.LeaguesRepositoryImpl
import com.core.data.repository.MatchesRepositoryImpl
import com.core.data.repository.auth.AuthRepositoryImpl
import com.core.data.repository.series.SeriesRepositoryImpl
import com.core.domain.repository.league.LeagueRepository
import com.core.domain.repository.MatchesRepository
import com.core.domain.repository.auth.AuthRepository
import com.core.domain.repository.favourite_league.FavouriteLeagueRepository
import com.core.domain.repository.series.SeriesRepository
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
    fun bindLeaguesRepository(leaguesRepositoryImpl: LeaguesRepositoryImpl): LeagueRepository

    @Binds
    @Singleton
    fun bindMatchesRepository(matchesRepositoryImpl: MatchesRepositoryImpl): MatchesRepository

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindSeriesRepository(seriesRepositoryImpl: SeriesRepositoryImpl): SeriesRepository

    @Binds
    @Singleton
    fun bindFavouriteLeagueRepository(favouriteLeaguesRepositoryImpl: FavouriteLeaguesRepositoryImpl): FavouriteLeagueRepository
}