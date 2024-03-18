package com.feature.match.screen.past_matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.live_matches.GetPastMatchesUseCase
import com.feature.match.event.past_matches.PastMatchesEvents
import com.feature.match.mapper.match.toPresentationModel
import com.feature.match.state.past_matches.PastMatchesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PastMatchesViewModel @Inject constructor(private val getPastMatchesUseCase: GetPastMatchesUseCase) :
    ViewModel() {

    private val _pastMatchesState = MutableStateFlow(PastMatchesState())
    val pastMatchesState get() = _pastMatchesState.asStateFlow()

    fun onEvent(event: PastMatchesEvents) {
        when (event) {
            PastMatchesEvents.FetchPastMatches -> fetchPastMatches()
            PastMatchesEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }


    private fun fetchPastMatches() {
        viewModelScope.launch {
            getPastMatchesUseCase().collect { res ->
                res.takeIfSuccess { data ->
                    _pastMatchesState.update {
                        it.copy(
                            matches = data.map { it.toPresentationModel() },
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
        _pastMatchesState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _pastMatchesState.update { it.copy(errorMessage = message) }
}