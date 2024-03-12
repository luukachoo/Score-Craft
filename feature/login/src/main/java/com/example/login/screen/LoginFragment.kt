package com.example.login.screen

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
import com.example.login.R
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.event.LoginEvent
import com.example.login.extension.showSnackBar
import com.example.login.state.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun bind() {
    }

    override fun bindViewActionListeners() {
        binding.apply {
            loginBtn.setOnClickListener {
                val email = emailEt.text.toString()
                val password = passwordEt.text.toString()

                viewModel.onEvent(LoginEvent.LogIn(email, password))
            }

            dontHaveAccTv.setOnClickListener {
                viewModel.navigateToRegister()
            }

            backBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.welcome/fragment_welcome")
            }

            forgotPasswordTv.setOnClickListener {
                viewModel.navigateToForgotPasswordPage()
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logInState.collect {
                    handleLogInState(logInState = it)
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

    private fun handleNavigationEvents(event: LoginFragmentViewModel.LogInUiEvent) {
        when (event) {
            LoginFragmentViewModel.LogInUiEvent.NavigateToForgotPasswordPage -> handleNavigation("market-mingle://feature.forgot_password/fragment_forgot_password")
            LoginFragmentViewModel.LogInUiEvent.NavigateToHome -> handleNavigation("market-mingle://feature.forgot_password/fragment_forgot_password")
            LoginFragmentViewModel.LogInUiEvent.NavigateToRegister -> handleNavigation("market-mingle://feature.register/fragment_register")
        }
    }

    private fun handleLogInState(logInState: LoginState) {
        binding.apply {
            progress.visibility =
                if (logInState.isLoading) View.VISIBLE else View.GONE

            logInState.errorMessage?.let {
                root.showSnackBar(message = it)
                viewModel.onEvent(LoginEvent.ResetErrorMessage)
            }
        }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.loginFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }
}