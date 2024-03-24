package com.example.tournament.screen.standings

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.core.common.extension.showSnackbar
import com.example.tournament.event.tournament_team_standings.TournamentTeamStandingEvents
import com.example.tournament.screen.standings.adapter.TeamStandingsAdapter
import com.example.tournament.screen.tournament_details.TournamentDetailsFragment
import com.example.tournament.state.tournament_team_standings.TournamentTeamStandingsState
import com.feature.tournament_details.databinding.FragmentTeamStandingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamStandingsFragment : BaseFragment<FragmentTeamStandingsBinding>(FragmentTeamStandingsBinding::inflate) {

    private val viewModel: TeamStandingsViewModel by viewModels()
    private val teamStandingsAdapter by lazy { TeamStandingsAdapter() }

    override fun bind() {
        setUpRecycler()
        val parentFragment = parentFragment

        if (parentFragment is TournamentDetailsFragment) {
            val slug = parentFragment.arguments?.getString("slug")
            viewModel.onEvent(TournamentTeamStandingEvents.FetchTeamStandings(slug!!))
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.teamStandingsViewState.collect {
                    handleTeamStandingsState(it)
                }
            }
        }
    }


    private fun handleTeamStandingsState(state: TournamentTeamStandingsState) = binding.apply {
        state.teams.let {
            teamStandingsAdapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(TournamentTeamStandingEvents.ResetErrorMessage)
        }
    }

    private fun setUpRecycler() {
        binding.rvTeamStandings.adapter = teamStandingsAdapter
    }
}