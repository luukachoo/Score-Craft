package com.core.data.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.core.domain.repository.datastore.DataStoreRepository
import com.google.android.datatransport.runtime.logging.Logging.d
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : DataStoreRepository {
    override suspend fun setDarkModeConfig(theme: String) {
        d("DataStoreRepositoryImpl", "Saved dark mode theme: $theme")
        dataStore.edit { preferences ->
            preferences[PreferencesKey.darkModeConfig] = theme
        }
    }

    override fun getDarkModeConfig(): Flow<String?> {
        return dataStore.data.map { preferences ->
            val theme = preferences[PreferencesKey.darkModeConfig]
            d("DataStoreRepositoryImpl", "Retrieved dark mode theme: $theme")
            theme
        }
    }
}
