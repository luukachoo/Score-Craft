package com.example.forgot_password.screen

import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.core.common.base.BaseFragment
import com.example.forgot_password.R
import com.example.forgot_password.databinding.FragmentForgotPasswordBinding
import com.example.forgot_password.event.ForgotPasswordEvent
import com.example.forgot_password.extension.showSnackBar
import com.example.forgot_password.state.ForgotPasswordState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun bind() {
    }

    override fun bindViewActionListeners() {
        binding.apply {
            sendCodeBtn.setOnClickListener {
                val email: String = emailEt.text.toString()

                viewModel.onEvent(ForgotPasswordEvent.ForgotPassword(email))
            }

            backBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.welcome/fragment_welcome")
            }
        }

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forgotPasswordState.collect {
                    handleForgotPasswordState(state = it)
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

    private fun handleForgotPasswordState(state: ForgotPasswordState) = binding.apply {
        progress.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.errorMessage?.let {
            root.showSnackBar(message = it)
            viewModel.onEvent(ForgotPasswordEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: ForgotPasswordViewModel.ForgotPasswordUiEvent) {
        when (event) {
            ForgotPasswordViewModel.ForgotPasswordUiEvent.NavigateToWelcome -> handleNavigation("market-mingle://feature.welcome/fragment_welcome")
            ForgotPasswordViewModel.ForgotPasswordUiEvent.NavigateToLogin -> handleNavigation("market-mingle://feature.login/fragment_login")
        }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.forgotPasswordFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }
}