package com.example.image_bottom_sheet.event.settings

sealed class SettingsEvent {
    data class SetDarkModeConfig(val config: String) : SettingsEvent()
}