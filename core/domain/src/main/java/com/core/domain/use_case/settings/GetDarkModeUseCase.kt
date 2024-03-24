package com.core.domain.use_case.settings

import com.core.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class GetDarkModeUseCase @Inject constructor(private val repository: DataStoreRepository) {
    operator fun invoke() =
        repository.getDarkModeConfig()
}