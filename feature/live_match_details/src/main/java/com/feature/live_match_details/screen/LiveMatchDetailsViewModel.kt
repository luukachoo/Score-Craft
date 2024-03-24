package com.feature.live_match_details.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.live_matches.GetMatchByIdUseCase
import com.core.domain.use_case.live_matches.GetMatchOpponentsUseCase
import com.feature.live_match_details.event.LiveMatchDetailsEvents
import com.feature.live_match_details.event.LiveMatchDetailsUiEvent
import com.feature.live_match_details.extension.toPlayersModel
import com.feature.live_match_details.mapper.toPresentationModel
import com.feature.live_match_details.state.LiveMatchDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveMatchDetailsViewModel @Inject constructor(
    private val getMatchByIdUseCase: GetMatchByIdUseCase,
    private val getMatchOpponentsUseCase: GetMatchOpponentsUseCase
) :
    ViewModel() {

    private val _liveMatchesState = MutableStateFlow(LiveMatchDetailsState())
    val liveMatchesState get() = _liveMatchesState.asStateFlow()

    private val _liveMatchUiEvent = MutableSharedFlow<LiveMatchDetailsUiEvent>()
    val liveMatchUiEvent get() = _liveMatchUiEvent.asSharedFlow()

    fun onEvent(event: LiveMatchDetailsEvents) {
        when (event) {
            is LiveMatchDetailsEvents.FetchMatchDetailsById -> fetchMatchDetailsById(event.matchId)
            is LiveMatchDetailsEvents.FetchTeamMembersByMatchId -> fetchOpponentsByMatchId(event.matchId)
            LiveMatchDetailsEvents.ResetErrorMessage -> updateErrorMessage(null)
            LiveMatchDetailsEvents.BackButtonClick -> updateNavigationEvent(
                LiveMatchDetailsUiEvent.NavigateToLives
            )
        }
    }

    private fun fetchMatchDetailsById(matchId: Int) {
        viewModelScope.launch {
            getMatchByIdUseCase(matchId).collect { res ->
                res.takeIfSuccess { match ->
                    _liveMatchesState.update {
                        it.copy(
                            matchDetails = match.toPresentationModel()
                        )
                    }
                }

                res.takeIfLoading { loading(it) }

                res.takeIfError { updateErrorMessage(it) }
            }
        }
    }

    private fun fetchOpponentsByMatchId(matchId: Int) {
        viewModelScope.launch {
            getMatchOpponentsUseCase(matchId).collect { res ->
                res.takeIfSuccess { getTeamWrapper ->
                    _liveMatchesState.update {
                        it.copy(
                            players = getTeamWrapper.toPresentationModel().toPlayersModel(),
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _liveMatchesState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _liveMatchesState.update { it.copy(errorMessage = message) }

    private fun updateNavigationEvent(events: LiveMatchDetailsUiEvent) = viewModelScope.launch {
        _liveMatchUiEvent.emit(events)
    }
}
