package com.feature.welcome

import com.core.common.base.BaseFragment
import com.core.common.helper.BindViewActionListeners
import com.feature.welcome.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate), BindViewActionListeners {
    override fun bind() {
        bindViewActionListeners()
    }

    override fun bindViewActionListeners() {

    }
}