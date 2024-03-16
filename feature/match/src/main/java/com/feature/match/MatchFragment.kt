package com.feature.match

import com.core.common.base.BaseFragment
import com.feature.match.databinding.FragmentMatchBinding
import com.google.android.material.tabs.TabLayoutMediator

class MatchFragment : BaseFragment<FragmentMatchBinding>(FragmentMatchBinding::inflate) {

    private var pagerAdapter: ViewPagerAdapter? = null

    override fun bind() {
        setUpPagerAdapter()
        setUpTabLayout()
    }

    private fun setUpTabLayout() = binding.apply {
        TabLayoutMediator(tabMatches, viewPagerMatches){ tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.past)
                1 -> tab.text = getString(R.string.live)
                2 -> tab.text = getString(R.string.upcoming)
            }
        }.attach()
    }

    private fun setUpPagerAdapter() {
        pagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPagerMatches.adapter = pagerAdapter

        binding.viewPagerMatches.offscreenPageLimit
    }
}