package com.example.splash_screen.screen

import android.animation.Animator
import android.app.AlertDialog
import android.content.Intent
import android.provider.Settings
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.example.splash_screen.databinding.FragmentSplashScreenBinding
import com.example.splash_screen.event.SplashScreenEvent
import com.example.splash_screen.extension.showSnackBar
import com.example.splash_screen.state.SplashScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.core.common.R

@AndroidEntryPoint
class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun bind() {
        binding.animationView.apply {
            speed = 2.0f
            addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    viewModel.onEvent(SplashScreenEvent.FetchUserDarkModePreference)
                }

                override fun onAnimationEnd(animation: Animator) {
                    viewModel.onEvent(SplashScreenEvent.CheckUserSessions)
                }

                override fun onAnimationCancel(animation: Animator) {
                    // Animation canceled
                }

                override fun onAnimationRepeat(animation: Animator) {
                    // Animation repeats
                }
            })
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashScreenState.collect {
                    handleRegisterState(state = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }
    }

    private fun handleRegisterState(state: SplashScreenState) = binding.apply {
        state.errorMessage?.let {
            root.showSnackBar(message = it)
            viewModel.onEvent(SplashScreenEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: SplashScreenViewModel.SplashScreenUiEvent) {
        when (event) {
            SplashScreenViewModel.SplashScreenUiEvent.NavigateToHome -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Home,
                true
            )

            SplashScreenViewModel.SplashScreenUiEvent.NavigateToWelcome -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Welcome,
                true
            )

            SplashScreenViewModel.SplashScreenUiEvent.ShowNetworkError -> showNetworkErrorDialog()
        }
    }

    private fun showNetworkErrorDialog() {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.no_internet_connection))
            .setMessage(getString(R.string.please_check_your_network_settings_and_try_again))
            .setPositiveButton(getString(R.string.retry)) { _, _ ->
                viewModel.onEvent(SplashScreenEvent.CheckUserSessions)
            }
            .setNegativeButton(getString(R.string.turn_on_wifi)) { _, _ ->
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            .setCancelable(false)
            .show()
    }
}