package com.core.domain.repository.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setDarkModeConfig(theme: String)
    fun getDarkModeConfig(): Flow<String?>
}