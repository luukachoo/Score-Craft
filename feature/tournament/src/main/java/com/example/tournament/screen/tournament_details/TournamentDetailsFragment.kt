package com.example.tournament.screen.tournament_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.R
import com.core.common.base.BaseFragment
import com.core.common.extension.loadImagesWithGlide
import com.core.common.extension.showSnackbar
import com.example.tournament.event.tournament_details.TournamentDetailUiEvent
import com.example.tournament.event.tournament_details.TournamentDetailsEvent
import com.example.tournament.screen.tournament_details.adapter.ViewPagerAdapter
import com.example.tournament.state.tournament_details.TournamentDetailsState
import com.feature.tournament_details.databinding.FragmentTournamentDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TournamentDetailsFragment :
    BaseFragment<FragmentTournamentDetailsBinding>(FragmentTournamentDetailsBinding::inflate) {

    private val viewModel: TournamentDetailsViewModel by viewModels()
    private var pagerAdapter: ViewPagerAdapter? = null

    override fun bind() {
        setUpPagerAdapter()
        setUpTabLayout()
        val arg = arguments?.getString("slug")
        viewModel.onEvent(TournamentDetailsEvent.FetchTournamentDetailsBySlug(arg!!))
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    handleTournamentDetailsState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleTournamentUiEvents(it)
                }
            }
        }
    }

    override fun bindViewActionListeners() {
        binding.backBtn.setOnClickListener {
            viewModel.onEvent(TournamentDetailsEvent.BackButtonClick)
        }
    }

    private fun handleTournamentDetailsState(state: TournamentDetailsState) = with(binding) {
        state.tournamentDetails?.let {
            ivLeagueLogo.loadImagesWithGlide(
                state.tournamentDetails.league.imageUrl,
                com.core.common.R.drawable.placeholder
            )
            tvLeagueName.text = state.tournamentDetails.name
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(TournamentDetailsEvent.ResetErrorMessage)
        }
    }

    private fun handleTournamentUiEvents(event: TournamentDetailUiEvent) {
        when(event) {
            TournamentDetailUiEvent.NavigateToTournaments -> findNavController().popBackStack()
        }
    }

    private fun setUpTabLayout() = binding.apply {
        TabLayoutMediator(tournamentTabs, vpTournamentDetails) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.standings)
                1 -> tab.text = getString(R.string.matches)
            }
        }.attach()
    }

    private fun setUpPagerAdapter() {
        pagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.vpTournamentDetails.adapter = pagerAdapter

        binding.vpTournamentDetails.offscreenPageLimit
    }
}