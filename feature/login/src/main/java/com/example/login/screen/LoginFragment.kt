package com.example.login.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.core.common.extension.showSnackbar
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.event.LoginEvent
import com.example.login.state.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun bind() {}

    override fun bindViewActionListeners() {
        val emailArg = arguments?.getString("email") ?: ""
        val passwordArg = arguments?.getString("password") ?: ""

        binding.apply {
            emailEt.setText(emailArg)
            passwordEt.setText(passwordArg)

            loginBtn.setOnClickListener {
                val email = emailEt.text.toString()
                val password = passwordEt.text.toString()

                viewModel.onEvent(LoginEvent.LogIn(email, password))
            }

            dontHaveAccTv.setOnClickListener {
                viewModel.navigateToRegister()
            }

            backBtn.setOnClickListener {
                findNavController().popBackStack()
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
            LoginFragmentViewModel.LogInUiEvent.NavigateToForgotPasswordPage -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.ForgotPassword
            )

            LoginFragmentViewModel.LogInUiEvent.NavigateToHome -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Home,
                true
            )

            LoginFragmentViewModel.LogInUiEvent.NavigateToRegister -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Register
            )
        }
    }

    private fun handleLogInState(logInState: LoginState) {
        binding.apply {
            progress.visibility =
                if (logInState.isLoading) View.VISIBLE else View.GONE

            logInState.errorMessage?.let {
                root.showSnackbar(message = it)
                viewModel.onEvent(LoginEvent.ResetErrorMessage)
            }
        }
    }
}