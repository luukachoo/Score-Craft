package com.example.registration

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.core.common.base.BaseFragment
import com.example.registration.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    override fun bind() {

    }

    override fun bindViewActionListeners() {
        binding.apply {
            registerBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.login/fragment_login")
            }

            backBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.welcome/fragment_welcome")
            }
        }
    }

    override fun bindObserves() {
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.registerFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }
}