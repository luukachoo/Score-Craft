package com.example.marketmingle.di

import com.core.data.repository.auth.AuthRepositoryImpl
import com.core.data.repository.friends.FriendRepositoryImpl
import com.core.data.repository.league.LeaguesRepositoryImpl
import com.core.data.repository.match.MatchesRepositoryImpl
import com.core.data.repository.messaging.MessagingRepositoryImpl
import com.core.data.repository.send_notification.SendNotificationRepositoryImpl
import com.core.data.repository.series.SeriesRepositoryImpl
import com.core.data.repository.tournament.TournamentRepositoryImpl
import com.core.data.repository.user.UserRepositoryImpl
import com.core.domain.repository.auth.AuthRepository
import com.core.domain.repository.friends.FriendRepository
import com.core.domain.repository.league.LeagueRepository
import com.core.domain.repository.match.MatchesRepository
import com.core.domain.repository.send_message.MessagingRepository
import com.core.domain.repository.send_notification.SendNotificationRepository
import com.core.domain.repository.series.SeriesRepository
import com.core.domain.repository.tournament.TournamentRepository
import com.core.domain.repository.user.UserRepository
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
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindAddFriendsRepository(friendRepositoryImpl: FriendRepositoryImpl): FriendRepository

    @Binds
    @Singleton
    fun bindSendNotificationRepository(sendNotificationRepositoryImpl: SendNotificationRepositoryImpl): SendNotificationRepository

    @Binds
    @Singleton
    fun bindTournamentRepository(tournamentRepositoryImpl: TournamentRepositoryImpl): TournamentRepository

    @Binds
    @Singleton
    fun bindSendMessageRepository(sendMessageRepositoryImpl: MessagingRepositoryImpl): MessagingRepository
}