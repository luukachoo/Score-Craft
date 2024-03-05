package com.feature.home.screen

import android.util.Log.d
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.showSnackbar
import com.feature.home.databinding.FragmentHomeBinding
import com.feature.home.event.HomeFragmentEvent
import com.feature.home.event.HomeNavigationEvents
import com.feature.home.recycler_adapters.MainRecyclerAdapter
import com.feature.home.state.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun bind() {
        mainRecyclerAdapter = MainRecyclerAdapter(emptyList())
        viewModel.onEvent(HomeFragmentEvent.FetchCategories)
    }

    override fun bindViewActionListeners() {
        mainRecyclerAdapter.onLeagueClick { league ->
            if (league.slug.isNotEmpty()) {
                viewModel.onEvent(HomeFragmentEvent.ItemClick(league.slug))
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleHomeState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeUiEvent.collect {
                    handleNavigationEvents(it)
                }
            }
        }
    }

    private fun handleHomeState(state: HomeState) = with(binding) {
        progressBar.isVisible = state.isLoading

        mainRecyclerAdapter = MainRecyclerAdapter(state.leagues ?: emptyList())
        mainRecyclerView.adapter = mainRecyclerAdapter

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(HomeFragmentEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: HomeNavigationEvents) {
        when(event) {
            is HomeNavigationEvents.NavigateToDetails -> handleNavigation(event.slug)
        }
    }

    private fun handleNavigation(slug: String) {
        if (slug.isNotEmpty()) {
            val uri = "market-mingle://feature.series/fragment_series?slug=$slug"
            val parsedUri = uri.toUri()
            val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()
            d("Navigation", "Navigating to: $parsedUri")
            findNavController().navigate(request)
        }
    }
}