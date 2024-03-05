package com.feature.home.screen

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.takeIfError
import com.core.common.resource.takeIfLoading
import com.core.common.resource.takeIfSuccess
import com.core.domain.use_case.GetLeaguesUseCase
import com.feature.home.event.HomeFragmentEvent
import com.feature.home.event.HomeNavigationEvents
import com.feature.home.mapper.toPresentationModel
import com.feature.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getLeaguesUseCase: GetLeaguesUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState get() = _homeState

    private val _homeUiEvent = MutableSharedFlow<HomeNavigationEvents>()
    val homeUiEvent: SharedFlow<HomeNavigationEvents> get() = _homeUiEvent

    fun onEvent(event: HomeFragmentEvent) {
        viewModelScope.launch {
            when (event) {
                HomeFragmentEvent.FetchCategories -> fetchLeagues()
                is HomeFragmentEvent.ItemClick -> updateNavigationEvent(
                    HomeNavigationEvents.NavigateToDetails(
                        event.slug
                    )
                )
                HomeFragmentEvent.ResetErrorMessage -> updateErrorMessage(message = null)
            }
        }
    }

    private suspend fun fetchLeagues() {
        viewModelScope.launch {
            getLeaguesUseCase().collectLatest { res ->
                res.takeIfError { errorMessage ->
                    updateErrorMessage(errorMessage)
                    d("ViewModelHttpError", errorMessage)
                }

                res.takeIfLoading { loading ->
                    loading(loading)
                }

                res.takeIfSuccess { data ->
                    _homeState.update {
                        it.copy(
                            leagues = data.map { getLeague -> getLeague.toPresentationModel() },
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _homeState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _homeState.update { it.copy(errorMessage = message) }

    private suspend fun updateNavigationEvent(events: HomeNavigationEvents) =
        _homeUiEvent.emit(events)
}