package com.example.tournament.screen.tournament_list

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.showSnackbar
import com.example.tournament.event.tournament_list.TournamentEvent
import com.example.tournament.event.tournament_list.TournamentNavigationEvents
import com.example.tournament.screen.tournament_list.adapter.TournamentRecyclerAdapter
import com.example.tournament.state.tournament_list.TournamentState
import com.feature.tournament_details.databinding.FragmentTournamentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TournamentFragment :
    BaseFragment<FragmentTournamentBinding>(FragmentTournamentBinding::inflate) {

    private val adapter by lazy { TournamentRecyclerAdapter() }
    private val viewModel: TournamentFragmentViewModel by viewModels()

    override fun bind() {
        setUpRecycler()
        val slugArg = arguments?.getString("slug")
        viewModel.onEvent(TournamentEvent.FetchTournaments(slugArg!!))
    }

    override fun bindViewActionListeners() {
        adapter.onClick {
            viewModel.onEvent(TournamentEvent.ItemClick(it.slug))
        }

        binding.backBtn.setOnClickListener {
            viewModel.onEvent(TournamentEvent.BackButtonClick)
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    handleTournamentState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tournamentUiState.collect {
                    handleNavigationEvents(it)
                }
            }
        }
    }

    private fun handleTournamentState(state: TournamentState) = binding.apply {
        progressBar.isVisible = state.isLoading

        state.tournaments.let {
            adapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(TournamentEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: TournamentNavigationEvents) {
        when (event) {
            is TournamentNavigationEvents.NavigateToDetails -> findNavController().navigate(
                TournamentFragmentDirections.actionTournamentFragmentToTournamentDetailsFragment(
                    event.slug
                )
            )

            TournamentNavigationEvents.NavigateToSeries -> findNavController().popBackStack()
        }
    }

    private fun setUpRecycler() {
        binding.rvTournaments.adapter = adapter
    }
}