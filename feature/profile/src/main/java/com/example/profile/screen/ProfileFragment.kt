package com.example.profile.screen

import android.annotation.SuppressLint
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
import com.example.profile.R
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.event.ProfileEvent
import com.example.profile.extension.loadImagesWithGlide
import com.example.profile.extension.showSnackBar
import com.example.profile.model.Users
import com.example.profile.state.ProfileState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileFragmentViewModel by viewModels()

    override fun bind() {
        viewModel.onEvent(ProfileEvent.GetCurrentUser)
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profileState.collect {
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

    private fun handleNavigationEvents(event: ProfileFragmentViewModel.ProfileUiEvent) {
        when (event) {
            ProfileFragmentViewModel.ProfileUiEvent.NavigateToHome -> handleNavigation("market-mingle://feature.home/fragment_home")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleRegisterState(state: ProfileState) = binding.apply {
        progress.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.user?.let {
            tvFullName.text = "${it.firstName} ${it.lastName}"
            tvUserName.text = "@${it.userName}"
        }

        state.errorMessage?.let {
            root.showSnackBar(message = it)
            viewModel.onEvent(ProfileEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.profileFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }
}