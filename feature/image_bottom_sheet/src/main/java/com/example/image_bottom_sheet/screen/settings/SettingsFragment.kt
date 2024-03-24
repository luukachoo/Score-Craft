package com.example.image_bottom_sheet.screen.settings

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
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                com.example.image_bottom_sheet.R.id.rbSystemDefault -> {
                    viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.system_default_lowercase)))
                }
                com.example.image_bottom_sheet.R.id.rbDark -> {
                    viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.dark_lowercase)))
                }
                com.example.image_bottom_sheet.R.id.rbLight -> {
                    viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.light_lowercase)))
                }
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.settingsState.collect {
                    handleSettingsState(it)
                }
            }
        }
    }

    private fun handleSettingsState(state: SettingsState) {
        // Temporarily remove listener to avoid triggering events when programmatically setting checked state
        binding.radioGroup.setOnCheckedChangeListener(null)

        when(state.darkThemeConfig) {
            getString(R.string.system_default_lowercase) -> binding.rbSystemDefault.isChecked = true
            getString(R.string.dark_lowercase) -> binding.rbDark.isChecked = true
            getString(R.string.light_lowercase) -> binding.rbLight.isChecked = true
            else -> binding.radioGroup.clearCheck()  // Clear selection if config is unknown or null
        }

        // Apply theme settings if needed
        applyThemeBasedOnConfig(state.darkThemeConfig)

        // Re-attach listener after updating the radio buttons
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbSystemDefault -> viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.system_default_lowercase)))
                R.id.rbDark -> viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.dark_lowercase)))
                R.id.rbLight -> viewModel.onEvent(SettingsEvent.SetDarkModeConfig(getString(R.string.light_lowercase)))
            }
        }
    }

    private fun applyThemeBasedOnConfig(config: String?) {
        when(config) {
            getString(R.string.system_default_lowercase) -> setSystemDefaultTheme()
            getString(R.string.dark_lowercase) -> setDarkTheme()
            getString(R.string.light_lowercase) -> setLightTheme()
        }
    }

    private fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setSystemDefaultTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}