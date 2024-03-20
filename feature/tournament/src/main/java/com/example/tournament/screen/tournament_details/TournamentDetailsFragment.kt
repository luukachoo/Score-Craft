package com.example.tournament.screen.tournament_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.core.common.extension.loadImagesWithGlide
import com.example.tournament.state.tournament_details.TournamentDetailsState
import com.feature.tournament_details.databinding.FragmentTournamentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TournamentDetailsFragment :
    BaseFragment<FragmentTournamentDetailsBinding>(FragmentTournamentDetailsBinding::inflate) {

    private val viewModel: TournamentDetailsViewModel by viewModels()

    override fun bind() {
        val arg = arguments?.getString("slug")
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    handleTournamentDetailsState(it)
                }
            }
        }
    }

    override fun bindViewActionListeners() {

    }

    private fun handleTournamentDetailsState(state: TournamentDetailsState) = with(binding) {
        state.tournamentDetails?.let {
            ivLeagueLogo.loadImagesWithGlide(
                state.tournamentDetails.league.imageUrl,
                com.core.common.R.drawable.placeholder
            )
            tvLeagueName.text = state.tournamentDetails.name
        }
    }
}