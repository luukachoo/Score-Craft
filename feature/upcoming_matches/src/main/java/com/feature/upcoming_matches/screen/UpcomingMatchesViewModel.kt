package com.feature.upcoming_matches.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.live_matches.GetUpcomingMatchesUseCase
import com.feature.upcoming_matches.event.UpcomingMatchesEvent
import com.feature.upcoming_matches.mapper.toPresentationModel
import com.feature.upcoming_matches.state.UpcomingMatchesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMatchesViewModel @Inject constructor(private val getUpcomingMatchesUseCase: GetUpcomingMatchesUseCase) :
    ViewModel() {

    private val _upcomingMatchesState = MutableStateFlow(UpcomingMatchesState())
    val upcomingMatchesState get() = _upcomingMatchesState.asStateFlow()

    fun onEvent(event: UpcomingMatchesEvent) {
        when (event) {
            UpcomingMatchesEvent.FetchUpcomingMatches -> fetchUpcomingMatches()
            UpcomingMatchesEvent.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchUpcomingMatches() {
        viewModelScope.launch {
            getUpcomingMatchesUseCase().collect { res ->
                res.takeIfSuccess { data ->
                    _upcomingMatchesState.update {
                        it.copy(
                            upcomingMatches = data.map { getUpcomingMatch -> getUpcomingMatch.toPresentationModel() },
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
        _upcomingMatchesState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _upcomingMatchesState.update { it.copy(errorMessage = message) }

}