package com.core.domain.use_case.settings

import com.core.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(private val repository: DataStoreRepository) {
    suspend operator fun invoke(theme: String) =
        repository.setDarkModeConfig(theme)
}