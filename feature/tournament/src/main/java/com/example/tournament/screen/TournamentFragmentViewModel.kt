package com.example.tournament.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.tournament.GetTournamentsUseCase
import com.example.tournament.event.TournamentEvent
import com.example.tournament.mapper.toPresentationModel
import com.example.tournament.state.TournamentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    fun onEvent(event: TournamentEvent) {
        when(event) {
            is TournamentEvent.FetchTournaments -> fetchTournaments(event.slug)
            TournamentEvent.ResetErrorMessage -> updateErrorMessage(null)
            is TournamentEvent.ItemClick -> TODO()
        }
    }

    private fun fetchTournaments(slug: String) {
        viewModelScope.launch {
            getTournamentsUseCase(slug = slug).collect { res ->
                res.takeIfSuccess { data ->
                    _viewState.update {
                        it.copy(
                            tournaments = data.map { it.toPresentationModel() },
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

//    private suspend fun updateNavigationEvent(events: HomeNavigationEvents) =
//        _homeUiEvent.emit(events)
}