package com.example.tournament.screen.test

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.core.common.extension.showSnackbar
import com.example.tournament.databinding.FragmentTournamentMatchesBinding
import com.example.tournament.event.tournament_matches.TournamentMatchesEvent
import com.example.tournament.screen.test.adapter.TournamentMatchesAdapter
import com.example.tournament.screen.tournament_details.TournamentDetailsFragment
import com.example.tournament.state.tournament_matches.TournamentMatchesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TournamentMatchesFragment :
    BaseFragment<FragmentTournamentMatchesBinding>(FragmentTournamentMatchesBinding::inflate) {

    private val viewModel: TournamentMatchesViewModel by viewModels()
    private val adapter by lazy { TournamentMatchesAdapter() }
    override fun bind() {
        setUpRecycler()
        val parentFragment = parentFragment
        if (parentFragment is TournamentDetailsFragment) {
            val slug = parentFragment.arguments?.getString("slug")
            viewModel.onEvent(TournamentMatchesEvent.FetchTournamentMatches(slug!!))
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tournamentMatchesState.collect {
                    handleTournamentMatchesState(it)
                }
            }
        }


    }

    private fun handleTournamentMatchesState(state: TournamentMatchesState) = binding.apply {
        state.matches.let {
            adapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(TournamentMatchesEvent.ResetErrorMessage)
        }
    }

    private fun setUpRecycler() {
        binding.rvMatches.adapter = adapter
    }
}