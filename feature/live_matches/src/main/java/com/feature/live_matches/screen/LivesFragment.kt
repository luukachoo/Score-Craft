package com.feature.live_matches.screen

import android.net.Uri
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.showSnackbar
import com.feature.live_matches.databinding.FragmentLivesBinding
import com.feature.live_matches.event.LiveFragmentUiEvent
import com.feature.live_matches.event.LivesFragmentEvent
import com.feature.live_matches.state.LiveState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LivesFragment : BaseFragment<FragmentLivesBinding>(FragmentLivesBinding::inflate) {

    private val viewModel: LivesFragmentViewModel by viewModels()
    private val livesAdapter by lazy { LivesRecyclerAdapter() }

    override fun bind() {
        setUpRecycler()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.liveState.collect {
                    handleLiveState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationState(it)
                }
            }
        }
    }

    override fun bindViewActionListeners() {
        livesAdapter.onClick { match ->
            viewModel.onEvent(LivesFragmentEvent.ItemClick(match.id))
        }
    }

    private fun handleLiveState(state: LiveState) = with(binding) {
        progressBar.isVisible = state.isLoading

        state.liveMatches?.let {
            livesAdapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(message = it)
            viewModel.onEvent(LivesFragmentEvent.ResetErrorMessage)
        }

        if (state.liveMatches?.isEmpty() == true) {
            lottieNoLivesAnimation.visibility = View.VISIBLE
            lottieNoLivesAnimation.isAnimating
            tvNoLives.visibility = View.VISIBLE
        }  else {
            tvNoLives.visibility = View.GONE
            lottieNoLivesAnimation.visibility = View.GONE
        }
    }

    private fun handleNavigationState(state: LiveFragmentUiEvent) {
        when(state) {
            is LiveFragmentUiEvent.NavigateToDetails -> handleNavigation(state.id)
        }
    }

    private fun setUpRecycler() = with(binding) {
        rvMatches.adapter = livesAdapter
        viewModel.onEvent(LivesFragmentEvent.FetchLiveMatches)
    }

    private fun handleNavigation(matchId: Int) {
        val deepLinkUri =
            Uri.parse("market-mingle://feature.fragment_live_match_details/fragment_live_match_details?matchId=$matchId")

        findNavController().navigate(deepLinkUri)
    }
}