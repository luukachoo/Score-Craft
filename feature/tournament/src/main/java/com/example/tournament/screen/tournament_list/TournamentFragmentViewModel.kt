package com.example.tournament.screen.tournament_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.tournament.GetTournamentsUseCase
import com.example.tournament.event.tournament_list.TournamentEvent
import com.example.tournament.event.tournament_list.TournamentNavigationEvents
import com.example.tournament.mapper.toPresentationModel
import com.example.tournament.state.tournament_list.TournamentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentFragmentViewModel @Inject constructor(
    private val getTournamentsUseCase: GetTournamentsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(TournamentState())
    val viewState get() = _viewState.asStateFlow()

    private val _tournamentUiState = MutableSharedFlow<TournamentNavigationEvents>()
    val tournamentUiState get() = _tournamentUiState.asSharedFlow()

    fun onEvent(event: TournamentEvent) {
        when (event) {
            is TournamentEvent.FetchTournaments -> fetchTournaments(event.slug)
            TournamentEvent.ResetErrorMessage -> updateErrorMessage(null)
            is TournamentEvent.ItemClick -> updateNavigationEvent(
                TournamentNavigationEvents.NavigateToDetails(
                    event.slug
                )
            )
        }

    }

    private fun fetchTournaments(slug: String) {
        viewModelScope.launch {
            getTournamentsUseCase(slug = slug).collect { res ->
                res.takeIfSuccess { data ->
                    _viewState.update {
                        it.copy(
                            tournaments = data.map { getTournament -> getTournament.toPresentationModel() },
                            errorMessage = null,
                            isLoading = false
                        )
                    }
                }

                res.takeIfLoading { loading(it) }

                res.takeIfError { updateErrorMessage(it) }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _viewState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _viewState.update { it.copy(errorMessage = message) }

    private fun updateNavigationEvent(events: TournamentNavigationEvents) {
        viewModelScope.launch {
            _tournamentUiState.emit(events)
        }
    }
}