package com.feature.live_match_details.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.live_matches.GetMatchByIdUseCase
import com.feature.live_match_details.event.MatchDetailsFragmentEvents
import com.feature.live_match_details.mapper.toPresentationModel
import com.feature.live_match_details.state.MatchDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailsFragmentViewModel @Inject constructor(
    private val getMatchByIdUseCase: GetMatchByIdUseCase,
//    private val getMatchOpponentsUseCase: GetMatchOpponentsUseCase
) : ViewModel() {

    private val _detailsViewState = MutableStateFlow(MatchDetailsViewState())
    val detailsViewState = _detailsViewState.asStateFlow()

//    private val _uiEvent = MutableSharedFlow<LiveFragmentUiEvent>()
//    val uiEvent get() = _uiEvent


    fun onEvent(event: MatchDetailsFragmentEvents) {
        when (event) {
            MatchDetailsFragmentEvents.BackButtonClick -> TODO()
            is MatchDetailsFragmentEvents.FetchMatchById -> fetchMatchById(event.matchId)
//            is MatchDetailsFragmentEvents.FetchMatchOpponents -> fetchMatchOpponents(event.matchId)
            MatchDetailsFragmentEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }


    private fun fetchMatchById(matchId: Int) {
        viewModelScope.launch {
            getMatchByIdUseCase(matchId).collect { res ->
                res.takeIfSuccess { match ->
                    _detailsViewState.update {
                        it.copy(
                            match = match.toPresentationModel(),
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
//
//    private fun fetchMatchOpponents(matchId: Int) {
//        viewModelScope.launch {
//            getMatchOpponentsUseCase(matchId).collect { res ->
//                res.takeIfSuccess { opponent ->
//                    _detailsViewState.update { state ->
//                        state.copy(
//                            players = opponent.toPlayersList(),
//                            isLoading = false,
//                            errorMessage = null
//                        )
//                    }
//                }
//
//                res.takeIfLoading { loading(it) }
//
//                res.takeIfError {
//                    updateErrorMessage(it)
//                }
//            }
//        }
//    }

    private fun loading(isLoading: Boolean) =
        _detailsViewState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _detailsViewState.update { it.copy(errorMessage = message) }

//    private suspend fun updateNavigationEvent(events: LiveFragmentUiEvent) =
//        _uiEvent.emit(events)

}