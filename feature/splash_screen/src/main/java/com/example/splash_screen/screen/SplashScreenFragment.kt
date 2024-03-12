package com.example.splash_screen.screen

import android.animation.Animator
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.core.common.base.BaseFragment
import com.example.splash_screen.R
import com.example.splash_screen.databinding.FragmentSplashScreenBinding
import com.example.splash_screen.event.SplashScreenEvent
import com.example.splash_screen.extension.showSnackBar
import com.example.splash_screen.state.SplashScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun bind() {
        binding.animationView.apply {
            speed = 2.0f
            addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
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

    override fun bindViewActionListeners() {

    }

    private fun handleRegisterState(state: SplashScreenState) = binding.apply {
        state.errorMessage?.let {
            root.showSnackBar(message = it)
            viewModel.onEvent(SplashScreenEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: SplashScreenViewModel.SplashScreenUiEvent) {
        when (event) {
            SplashScreenViewModel.SplashScreenUiEvent.NavigateToHome -> handleNavigation("market-mingle://feature.home/fragment_home")
            SplashScreenViewModel.SplashScreenUiEvent.NavigateToWelcome -> handleNavigation("market-mingle://feature.welcome/fragment_welcome")
        }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.splashScreenFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }
}