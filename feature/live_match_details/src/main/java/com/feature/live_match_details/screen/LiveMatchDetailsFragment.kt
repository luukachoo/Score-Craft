package com.feature.live_match_details.screen

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.core.common.extension.loadImagesWithGlide
import com.core.common.extension.showSnackbar
import com.feature.live_match_details.databinding.FragmentLiveMatchDetailsBinding
import com.feature.live_match_details.event.LiveMatchDetailsEvents
import com.feature.live_match_details.state.LiveMatchDetailsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LiveMatchDetailsFragment :
    BaseFragment<FragmentLiveMatchDetailsBinding>(FragmentLiveMatchDetailsBinding::inflate) {

    private val viewModel: LiveMatchDetailsViewModel by viewModels()

    override fun bind() {
        val matchId = arguments?.getInt("matchId")
        viewModel.onEvent(LiveMatchDetailsEvents.FetchMatchDetailsById(matchId!!))
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.liveMatchesState.collect {
                    handleMatchDetailsViewState(it)
                }
            }
        }
    }


    private fun handleMatchDetailsViewState(state: LiveMatchDetailsState) {
        successState(state)
        loadingState(state)
        errorState(state)
    }

    private fun successState(state: LiveMatchDetailsState) = with(binding) {
        if (state.matchDetails?.status == "running") {
            buttonWatchOnTwitch.isEnabled = true
            buttonWatchOnYT.isEnabled = true
        } else {
            buttonWatchOnTwitch.isEnabled = false
            buttonWatchOnYT.isEnabled = false
        }

        progressBar.isVisible = state.isLoading
        tvTitle.text = state.matchDetails?.name
        tvTeamOneScore.text = state.matchDetails?.results?.first()?.score.toString()
        tvSecondTeamScore.text = state.matchDetails?.results?.last()?.score.toString()
        ivTeamOneImage.loadImagesWithGlide(state.matchDetails?.opponents?.first()?.opponent?.imageUrl)
        ivTeamSecondImage.loadImagesWithGlide(state.matchDetails?.opponents?.last()?.opponent?.imageUrl)
    }

    private fun errorState(state: LiveMatchDetailsState) =
        state.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(LiveMatchDetailsEvents.ResetErrorMessage)
        }

    private fun loadingState(state: LiveMatchDetailsState) = with(binding) {
        progressBar.isVisible = state.isLoading
    }


}