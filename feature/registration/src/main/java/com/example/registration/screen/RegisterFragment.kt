package com.example.registration.screen

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
import com.example.registration.R
import com.example.registration.databinding.FragmentRegisterBinding
import com.example.registration.event.RegisterEvent
import com.example.registration.extension.showSnackBar
import com.example.registration.state.RegisterState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun bind() {

    }

    override fun bindViewActionListeners() {
        binding.apply {
            registerBtn.setOnClickListener {
                val userName = userNameEt.text.toString()
                val firstName = firstNameEt.text.toString()
                val lastName = lastNameEt.text.toString()
                val email = emailEt.text.toString()
                val password = passwordEt.text.toString()
                val confirmPassword = confirmPasswordEt.text.toString()

                viewModel.onEvent(
                    RegisterEvent.Register(
                        userName,
                        firstName,
                        lastName,
                        email,
                        password,
                        confirmPassword
                    )
                )
            }

            alreadyHaveAccTv.setOnClickListener {
                viewModel.onAlreadyHaveAccountClicked()
            }

            backBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.welcome/fragment_welcome")
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect {
                    handleRegisterState(registerState = it)
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

    private fun handleRegisterState(registerState: RegisterState) = binding.apply {
        progress.visibility =
            if (registerState.isLoading) View.VISIBLE else View.GONE

        registerState.errorMessage?.let {
            root.showSnackBar(message = it)
            viewModel.onEvent(RegisterEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: RegisterFragmentViewModel.RegisterUiEvent) {
        when (event) {
            RegisterFragmentViewModel.RegisterUiEvent.AlreadyHaveAccountNavigation -> handleNavigation(
                "market-mingle://feature.login/fragment_login"
            )

            is RegisterFragmentViewModel.RegisterUiEvent.NavigateToLogin -> handleNavigationWithArgs(
                event.email,
                event.password
            )
        }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.registerFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }

    private fun handleNavigationWithArgs(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            val uri = "market-mingle://feature.login/fragment_login?email=$email&password=$password"
            val parsedUri = uri.toUri()
            val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

            val navOptions = navOptions {
                popUpTo(R.id.registerFragment) { inclusive = true }
            }

            findNavController().navigate(request, navOptions)
        }
    }
}