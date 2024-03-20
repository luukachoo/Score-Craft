package com.example.tournament.screen.tournament_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.tournament.GetTournamentDetailsUseCase
import com.example.tournament.event.tournament_details.TournamentDetailsEvent
import com.example.tournament.mapper.toPresentationModel
import com.example.tournament.state.tournament_details.TournamentDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentDetailsViewModel @Inject constructor(
    private val getTournamentDetailsUseCase: GetTournamentDetailsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(TournamentDetailsState())
    val viewState = _viewState.asStateFlow()

    fun onEvent(event: TournamentDetailsEvent) {
        when(event) {
            TournamentDetailsEvent.BackButtonClick -> TODO()
            is TournamentDetailsEvent.FetchTournamentDetailsBySlug -> fetchTournamentDetailsBySlug(event.slug)
            TournamentDetailsEvent.ResetErrorMessage -> updateErrorMessage(null)
        }
    }


    private fun fetchTournamentDetailsBySlug(slug: String) {
        viewModelScope.launch {
            getTournamentDetailsUseCase(slug = slug).collect { res ->
                res.takeIfSuccess {  getTournament ->
                    _viewState.update {
                        it.copy(
                            tournamentDetails = getTournament.toPresentationModel(),
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
        _viewState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _viewState.update { it.copy(errorMessage = message) }
}