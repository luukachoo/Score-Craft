package com.feature.match

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.feature.live_matches.screen.LivesFragment
import com.feature.past_matches.screen.PastMatchesFragment
import com.feature.upcoming_matches.screen.UpcomingMatchesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PastMatchesFragment()
            1 -> LivesFragment()
            else -> UpcomingMatchesFragment()
        }
    }
}