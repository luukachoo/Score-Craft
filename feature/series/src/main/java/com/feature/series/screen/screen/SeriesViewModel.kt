package com.feature.series.screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.series.GetSeriesUseCase
import com.feature.series.screen.event.SeriesEvent
import com.feature.series.screen.mapper.toPresenter
import com.feature.series.screen.state.SeriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase
) : ViewModel() {
    private val _seriesState = MutableStateFlow(SeriesState())
    val seriesState: StateFlow<SeriesState> = _seriesState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<SeriesUiEvent>()
    val uiEvent: SharedFlow<SeriesUiEvent> get() = _uiEvent

    fun onEvent(event: SeriesEvent) {
        viewModelScope.launch {
            when (event) {
                is SeriesEvent.FetchSeriesBySlug -> fetchSeriesBySlug(event.slug)
                SeriesEvent.ResetErrorMessage -> updateErrorMessage(message = null)
                SeriesEvent.NavigateToHome -> updateNavigationEvent(SeriesUiEvent.NavigateToHome)
            }
        }
    }

    private fun fetchSeriesBySlug(slug: String) {
        viewModelScope.launch {
            getSeriesUseCase(slug).collect { resource ->
                when (resource) {
                    is Resource.Error -> updateErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _seriesState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }

                    is Resource.Success -> {
                        _seriesState.update { it ->
                            it.copy(
                                series = resource.data.map {
                                    it.toPresenter()
                                },
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) =
        _seriesState.update { it.copy(errorMessage = message) }

    private suspend fun updateNavigationEvent(event: SeriesUiEvent) =
        _uiEvent.emit(event)

    sealed interface SeriesUiEvent {
        data object NavigateToHome : SeriesUiEvent
        data object NavigateToTournament : SeriesUiEvent
    }
}