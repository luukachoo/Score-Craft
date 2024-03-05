package com.feature.live_matches.screen

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.GetMatchesUseCase
import com.feature.live_matches.event.LivesFragmentEvent
import com.feature.live_matches.mapper.toPresentationModel
import com.feature.live_matches.state.LiveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LivesFragmentViewModel @Inject constructor(private val getMatchesUseCase: GetMatchesUseCase) :
    ViewModel() {

    private val _liveState = MutableStateFlow(LiveState())
    val liveState get() = _liveState

    fun onEvent(event: LivesFragmentEvent) {
        when (event) {
            LivesFragmentEvent.FetchLiveMatches -> fetchLiveMatches()
            is LivesFragmentEvent.ItemClick -> TODO()
            LivesFragmentEvent.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchLiveMatches() {
        viewModelScope.launch {
            getMatchesUseCase().collect { res ->
                res.takeIfSuccess { data ->
                    _liveState.update {
                        it.copy(
                            liveMatches = data.map { getMatch -> getMatch.toPresentationModel() }
                        )
                    }
                }

                res.takeIfLoading { loading ->
                    loading(loading)
                }

                res.takeIfError { errorMessage ->
                    updateErrorMessage(errorMessage)
                    d("ViewModelHttpError", errorMessage)
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _liveState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _liveState.update { it.copy(errorMessage = message) }

//    private suspend fun updateNavigationEvent(events: HomeNavigationEvents) =
//        _homeUiEvent.emit(events)

}