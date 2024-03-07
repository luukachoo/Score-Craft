package com.feature.live_match_details.screen

import android.content.Intent
import android.net.Uri
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
    private val rvPlayersAdapter by lazy { LiveMatchRecyclerAdapter() }
    private var twitchStreamLink = ""
    private var youtubeStreamLink = ""

    override fun bind() {
        setUpRecycler()
        val matchId = arguments?.getInt("matchId")
        viewModel.onEvent(LiveMatchDetailsEvents.FetchMatchDetailsById(matchId!!))
        viewModel.onEvent(LiveMatchDetailsEvents.FetchTeamMembersByMatchId(matchId))
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

    override fun bindViewActionListeners() = with(binding) {
        buttonWatchOnTwitch.setOnClickListener {
            handleTwitchLink(twitchStreamLink)
        }

        buttonWatchOnYT.setOnClickListener {
            val test = youtubeStreamLink
            handleYoutubeLink(youtubeStreamLink)
        }
    }

    private fun handleTwitchLink(twitchLink: String) {
        try{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(twitchLink)
            startActivity(intent)
        }catch (e: Throwable){
            binding.root.showSnackbar("Live is not available")
        }
    }

    private fun handleYoutubeLink(youtubeLink: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(youtubeLink)
            startActivity(intent)
        } catch (e: Throwable) {
            binding.root.showSnackbar("Live is not available")
        }
    }


    private fun handleMatchDetailsViewState(state: LiveMatchDetailsState) {
        successState(state)
        loadingState(state)
        errorState(state)
    }

    private fun successState(state: LiveMatchDetailsState) = with(binding) {
        val youtubeStream = state.matchDetails?.streamsList?.find { it.rawUrl.contains("https://www.youtube.com") }

        if (state.matchDetails?.streamsList != null) {
            twitchStreamLink = state.matchDetails.streamsList.last().rawUrl
        } else if (youtubeStream != null) {
            youtubeStreamLink = youtubeStream.toString()
        }

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

        rvPlayersAdapter.submitList(state.players)
    }

    private fun errorState(state: LiveMatchDetailsState) =
        state.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(LiveMatchDetailsEvents.ResetErrorMessage)
        }

    private fun loadingState(state: LiveMatchDetailsState) = with(binding) {
        progressBar.isVisible = state.isLoading
    }

    private fun setUpRecycler() = with(binding) {
        rvTeamPlayers.adapter = rvPlayersAdapter
    }
}