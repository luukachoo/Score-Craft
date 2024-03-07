package com.feature.live_match_details.screen

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.core.common.extension.loadImagesWithGlide
import com.core.common.extension.showSnackbar
import com.feature.live_match_details.databinding.FragmentMatchDetailsBinding
import com.feature.live_match_details.event.MatchDetailsFragmentEvents
import com.feature.live_match_details.state.MatchDetailsViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchDetailsFragment :
    BaseFragment<FragmentMatchDetailsBinding>(FragmentMatchDetailsBinding::inflate) {

    private val viewModel: MatchDetailsFragmentViewModel by viewModels()
    private val rvPlayersAdapter by lazy { MatchPlayersRecyclerAdapter() }

    override fun bind() {
        val matchId = arguments?.getInt("matchId")
        viewModel.onEvent(MatchDetailsFragmentEvents.FetchMatchById(matchId!!))
//        viewModel.onEvent(MatchDetailsFragmentEvents.FetchMatchOpponents(matchId))
        bindRecyclerView()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailsViewState.collect {
                    handleMatchDetailsViewState(it)
                }
            }
        }
    }


    private fun handleMatchDetailsViewState(state: MatchDetailsViewState) {
        successState(state)
        loadingState(state)
        errorState(state)
    }

    private fun successState(state: MatchDetailsViewState) = with(binding) {
        if (state.match?.status == "running") {
            buttonWatchOnTwitch.isEnabled = true
            buttonWatchOnYT.isEnabled = true
        }
        progressBar.isVisible = state.isLoading
        tvTitle.text = state.match?.name
        tvTeamOneScore.text = state.match?.results?.first()?.score.toString()
        tvSecondTeamScore.text = state.match?.results?.last()?.score.toString()
        ivTeamOneImage.loadImagesWithGlide(state.match?.opponents?.first()?.opponents?.first()?.imageUrl)
        ivTeamSecondImage.loadImagesWithGlide(state.match?.opponents?.last()?.opponents?.last()?.imageUrl)

        rvPlayersAdapter.submitList(state.players)
    }

    private fun errorState(state: MatchDetailsViewState) =
        state.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(MatchDetailsFragmentEvents.ResetErrorMessage)
        }

    private fun loadingState(state: MatchDetailsViewState) = with(binding) {
        progressBar.isVisible = state.isLoading
    }


    private fun bindRecyclerView() = with(binding) {
        rvTeamPlayers.adapter = rvPlayersAdapter
    }
}