package com.example.marketmingle.di

import com.core.data.repository.add_friends.AddFriendsRepositoryImpl
import com.core.data.repository.auth.AuthRepositoryImpl
import com.core.data.repository.favorite_leagues.FavouriteLeaguesRepositoryImpl
import com.core.data.repository.league.LeaguesRepositoryImpl
import com.core.data.repository.match.MatchesRepositoryImpl
import com.core.data.repository.send_notification.SendNotificationRepositoryImpl
import com.core.data.repository.series.SeriesRepositoryImpl
import com.core.domain.repository.MatchesRepository
import com.core.domain.repository.add_friend.AddFriendRepository
import com.core.domain.repository.auth.AuthRepository
import com.core.domain.repository.favourite_league.FavouriteLeagueRepository
import com.core.domain.repository.league.LeagueRepository
import com.core.domain.repository.send_notification.SendNotificationRepository
import com.core.domain.repository.series.SeriesRepository
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Binds
import dagger.Module
import dagger.Provides
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

    @Binds
    @Singleton
    fun bindAddFriendsRepository(addFriendsRepositoryImpl: AddFriendsRepositoryImpl): AddFriendRepository

    @Binds
    @Singleton
    fun bindSendNotificationRepository(sendNotificationRepositoryImpl: SendNotificationRepositoryImpl): SendNotificationRepository
}