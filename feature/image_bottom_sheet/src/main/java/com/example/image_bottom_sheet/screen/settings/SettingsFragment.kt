package com.example.image_bottom_sheet.screen.settings

import android.util.Log.d
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.R
import com.core.common.base.BaseBottomSheetFragment
import com.example.image_bottom_sheet.databinding.FragmentSettingsBinding
import com.example.image_bottom_sheet.event.settings.SettingsEvent
import com.example.image_bottom_sheet.state.SettingsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : BaseBottomSheetFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel: SettingsFragmentViewModel by viewModels()

    override fun bind() {
        d("ThemeDebug", "Binding fragment settings")
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                com.example.image_bottom_sheet.R.id.tbSystemDefault -> {
                    d("ThemeDebug", "System default selected")
                    viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.system_default_lowercase)))
                }
                com.example.image_bottom_sheet.R.id.rbDark -> {
                    d("ThemeDebug", "Dark selected")
                    viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.dark_lowercase)))
                }
                com.example.image_bottom_sheet.R.id.rbLight -> {
                    d("ThemeDebug", "Light selected")
                    viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.light_lowercase)))
                }
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.settingsState.collect {
                    d("ThemeDebug", "ViewModel state updated: $it")
                    handleSettingsState(it)
                }
            }
        }
    }

    private fun handleSettingsState(state: SettingsState) {
        d("ThemeDebug", "Current theme: ${state.darkThemeConfig}")
        when(state.darkThemeConfig) {
            getString(R.string.system_default_lowercase) -> setSystemDefaultTheme()
            getString(R.string.dark_lowercase) -> setDarkTheme()
            getString(R.string.light_lowercase) -> setLightTheme()
        }
    }

    private fun setDarkTheme() {
        d("ThemeDebug", "Setting dark theme")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun setLightTheme() {
        d("ThemeDebug", "Setting light theme")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setSystemDefaultTheme() {
        d("ThemeDebug", "Setting system default theme")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}