package com.example.marketmingle.di

import com.core.common.resource.HandleRetrofitResponse
import com.core.data.service.LeaguesService
import com.core.data.service.MatchesService
import com.example.marketmingle.BuildConfig
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

    @Singleton
    @Provides
    fun provideRetrofitProduct(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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
    fun provideLeaguesService(retrofit: Retrofit): LeaguesService {
        return retrofit.create(LeaguesService::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchesService(retrofit: Retrofit): MatchesService {
        return retrofit.create(MatchesService::class.java)
    }
}