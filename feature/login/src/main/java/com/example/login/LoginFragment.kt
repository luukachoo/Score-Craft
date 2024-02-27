package com.example.login

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.core.common.base.BaseFragment
import com.example.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun bind() {
    }

    override fun bindViewActionListeners() {
        binding.apply {
            loginBtn.setOnClickListener {
//                navigate to home screen
            }

            backBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.welcome/fragment_welcome")
            }

            dontHaveAccTv.setOnClickListener {
                handleNavigation("market-mingle://feature.register/fragment_register")
            }
        }
    }

    override fun bindObserves() {
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