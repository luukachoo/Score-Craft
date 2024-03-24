package com.example.registration.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.example.registration.databinding.FragmentRegisterBinding
import com.example.registration.event.RegisterEvent
import com.example.registration.extension.showSnackBar
import com.example.registration.state.RegisterState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun bind() {}

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
                viewModel.onEvent(RegisterEvent.OnAlreadyRegisteredClick)
            }

            backBtn.setOnClickListener {
                viewModel.onEvent(RegisterEvent.OnBackButtonClick)
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
            RegisterFragmentViewModel.RegisterUiEvent.NavigateToLogin -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.LoginWithoutArgument
            )

            is RegisterFragmentViewModel.RegisterUiEvent.NavigateToLoginWithArgs -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Login(event.email, event.password), true
            )

            RegisterFragmentViewModel.RegisterUiEvent.NavigateToWelcome -> findNavController().popBackStack()
        }
    }
}