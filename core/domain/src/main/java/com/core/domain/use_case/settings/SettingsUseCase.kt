package com.core.domain.use_case.settings

import javax.inject.Inject

data class SettingsUseCase @Inject constructor(
    val getDarkModeUseCase: GetDarkModeUseCase,
    val setDarkModeUseCase: SetDarkModeUseCase
)
