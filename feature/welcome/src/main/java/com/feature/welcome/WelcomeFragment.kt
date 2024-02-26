package com.feature.welcome

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.core.common.base.BaseFragment
import com.core.common.helper.BindViewActionListeners
import com.feature.welcome.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun bind() {

    }

    override fun bindViewActionListeners() {
        binding.apply {
            registerBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.register/fragment_register")
            }

            loginBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.login/fragment_login")
            }
        }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.welcomeFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }

    override fun bindObserves() {

    }
}