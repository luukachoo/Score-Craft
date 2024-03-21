package com.feature.match.screen.live_matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.live_matches.GetMatchesUseCase
import com.feature.match.event.live_matches.LiveFragmentUiEvent
import com.feature.match.event.live_matches.LivesFragmentEvent
import com.feature.match.mapper.match.live.toPresentationModel
import com.feature.match.state.live_matches.LiveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LivesFragmentViewModel @Inject constructor(private val getMatchesUseCase: GetMatchesUseCase) :
    ViewModel() {

    private val _liveState = MutableStateFlow(LiveState())
    val liveState get() = _liveState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LiveFragmentUiEvent>()
    val uiEvent get() = _uiEvent

    fun onEvent(event: LivesFragmentEvent) {
        when (event) {
            LivesFragmentEvent.FetchLiveMatches -> fetchLiveMatches()
            is LivesFragmentEvent.ItemClick -> updateNavigationEvent(
                LiveFragmentUiEvent.NavigateToDetails(
                    event.id
                )
            )

            LivesFragmentEvent.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchLiveMatches() {
        viewModelScope.launch {
            getMatchesUseCase().collect { res ->
                res.takeIfSuccess { data ->
                    _liveState.update {
                        it.copy(
                            liveMatches = data.map { getMatchWrapper -> getMatchWrapper.match.toPresentationModel() }
                        )
                    }
                }

                res.takeIfLoading { loading ->
                    loading(loading)
                }

                res.takeIfError { errorMessage ->
                    updateErrorMessage(errorMessage)
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _liveState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _liveState.update { it.copy(errorMessage = message) }

    private fun updateNavigationEvent(events: LiveFragmentUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(events)
        }
    }
}