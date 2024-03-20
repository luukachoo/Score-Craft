package com.feature.welcome

import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.feature.welcome.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun bind() {
        binding.animationView.isAnimating
    }

    override fun bindViewActionListeners() {
        binding.apply {
            registerBtn.setOnClickListener {
                findNavController().deepLinkNavigateTo(DeepLinkDestination.Register, true)
            }

            loginBtn.setOnClickListener {
                findNavController().deepLinkNavigateTo(
                    DeepLinkDestination.LoginWithoutArgument,
                    true
                )
            }
        }
    }
}