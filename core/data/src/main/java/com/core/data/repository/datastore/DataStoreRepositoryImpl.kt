package com.core.data.repository.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.core.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : DataStoreRepository {
    override suspend fun setDarkModeConfig(theme: String) {
        Log.d("DataStoreRepositoryImpl", "Setting dark mode config: $theme")
        dataStore.edit { preferences ->
            preferences[PreferencesKey.darkModeConfig] = theme
        }
    }

    override fun getDarkModeConfig(): Flow<String?> {
        Log.d("DataStoreRepositoryImpl", "Getting dark mode config")
        return dataStore.data.map { preferences ->
            preferences[PreferencesKey.darkModeConfig]
        }
    }
}
