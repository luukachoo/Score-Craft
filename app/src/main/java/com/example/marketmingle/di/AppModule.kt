package com.example.marketmingle.di

import com.core.common.resource.retrofit.HandleRetrofitResponse
import com.core.data.service.LeaguesService
import com.core.data.service.MatchesService
import com.core.data.service.SeriesService
import com.core.data.service.TournamentsService
import com.core.data.service.send_notification.SendNotificationService
import com.example.marketmingle.BuildConfig
import com.example.marketmingle.helper.annotation.PandaScoreRetrofit
import com.example.marketmingle.helper.annotation.PushNotificationRetrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    @PandaScoreRetrofit
    fun provideRetrofitProduct(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_PANDA_SCORE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @PushNotificationRetrofit
    fun provideRetrofitPushNotification(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_PUSH_NOTIFICATION_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideHandleResponse(): HandleRetrofitResponse {
        return HandleRetrofitResponse()
    }

    @Provides
    @Singleton
    fun provideLeaguesService(@PandaScoreRetrofit retrofit: Retrofit): LeaguesService {
        return retrofit.create(LeaguesService::class.java)
    }

    @Provides
    @Singleton
    fun provideSeriesService(@PandaScoreRetrofit retrofit: Retrofit): SeriesService {
        return retrofit.create(SeriesService::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchesService(@PandaScoreRetrofit retrofit: Retrofit): MatchesService {
        return retrofit.create(MatchesService::class.java)
    }

    @Provides
    @Singleton
    fun provideTournamentsService(@PandaScoreRetrofit retrofit: Retrofit): TournamentsService {
        return retrofit.create(TournamentsService::class.java)
    }

    @Provides
    @Singleton
    fun provideSendNotificationService(@PushNotificationRetrofit retrofit: Retrofit): SendNotificationService {
        return retrofit.create(SendNotificationService::class.java)
    }
}