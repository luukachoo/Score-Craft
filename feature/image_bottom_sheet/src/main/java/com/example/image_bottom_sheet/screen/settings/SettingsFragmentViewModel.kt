package com.example.image_bottom_sheet.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.use_case.settings.SettingsUseCase
import com.example.image_bottom_sheet.event.settings.SettingsEvent
import com.example.image_bottom_sheet.state.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsFragmentViewModel @Inject constructor(private val settingsUseCase: SettingsUseCase): ViewModel() {
    private val _settingsState = MutableStateFlow(SettingsState())
    val settingsState get() = _settingsState.asStateFlow()

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SetDarkModeConfig -> {
                viewModelScope.launch {
                    settingsUseCase.setDarkModeUseCase(event.config)
                    _settingsState.update {
                        it.copy(
                            darkThemeConfig = event.config
                        )
                    }
                }
            }
        }
    }
}