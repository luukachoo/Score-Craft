package com.example.tournament.screen.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.tournament.GetTeamStandingsUseCase
import com.example.tournament.event.tournament_team_standings.TournamentTeamStandingEvents
import com.example.tournament.mapper.toPresentationModel
import com.example.tournament.state.tournament_team_standings.TournamentTeamStandingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamStandingsViewModel @Inject constructor(
    private val getTeamStandingsUseCase: GetTeamStandingsUseCase
) : ViewModel() {

    private val _teamStandingsViewState = MutableStateFlow(TournamentTeamStandingsState())
    val teamStandingsViewState get() = _teamStandingsViewState.asStateFlow()

    fun onEvent(event: TournamentTeamStandingEvents) {
        when(event) {
            is TournamentTeamStandingEvents.FetchTeamStandings -> fetchTeamStandings(event.slug)
            TournamentTeamStandingEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }


    private fun fetchTeamStandings(slug: String) {
        viewModelScope.launch {
            getTeamStandingsUseCase(slug).collect { res ->
                res.takeIfSuccess { data ->
                    _teamStandingsViewState.update {
                        it.copy(
                            teams = data.map { getTeamStanding ->  getTeamStanding.toPresentationModel() },
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                res.takeIfLoading { loading(it) }

                res.takeIfError { updateErrorMessage(it) }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _teamStandingsViewState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _teamStandingsViewState.update { it.copy(errorMessage = message) }

}