package com.feature.match.screen.matches.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.feature.match.screen.live_matches.LivesFragment
import com.feature.match.screen.past_matches.PastMatchesFragment
import com.feature.match.screen.upcoming_matches.UpcomingMatchesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PastMatchesFragment()
            1 -> LivesFragment()
            else -> UpcomingMatchesFragment()
        }
    }
}