package com.feature.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.leagues.LeagueUseCase
import com.core.domain.use_case.user.GetUserUseCase
import com.feature.home.event.HomeFragmentEvent
import com.feature.home.event.HomeNavigationEvents
import com.feature.home.mapper.auth.toPresenter
import com.feature.home.mapper.toDomain
import com.feature.home.mapper.toPresentationModel
import com.feature.home.model.League
import com.feature.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val leaguesUseCase: LeagueUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState get() = _homeState

    private val _uiEvent = MutableSharedFlow<HomeNavigationEvents>()
    val uiEvent: SharedFlow<HomeNavigationEvents> get() = _uiEvent

    private var currentApiPage = 1
    private var currentLocalPage = 1
    private val pageSize = 100
    private val localItemsPerPage = 20
    private var items = mutableListOf<League>()
    private var isLastPage = false

    fun onEvent(event: HomeFragmentEvent) {
        viewModelScope.launch {
            when (event) {
                HomeFragmentEvent.FetchCategories -> fetchLeagues()
                is HomeFragmentEvent.OnLeagueClick -> updateNavigationEvent(
                    HomeNavigationEvents.NavigateToSeries(
                        event.slug
                    )
                )

                HomeFragmentEvent.ResetErrorMessage -> updateErrorMessage(message = null)
                HomeFragmentEvent.GetCurrentUser -> getCurrentUser()
                HomeFragmentEvent.LoadNextPage -> loadNextPage()
                HomeFragmentEvent.LoadPreviousPage -> loadPreviousPage()
                is HomeFragmentEvent.SaveFavouriteLeague -> saveFavouriteLeague(event.league)
                HomeFragmentEvent.OnProfileClick -> updateNavigationEvent(HomeNavigationEvents.NavigateToProfile)
            }
        }
    }

    private fun fetchLeagues() {
        viewModelScope.launch {
            if (isLastPage) {
                return@launch
            }

            val localPagesDisplayed = (items.size / localItemsPerPage) - (currentApiPage - 1) * (pageSize / localItemsPerPage)

            if (currentLocalPage > localPagesDisplayed) {
                leaguesUseCase.getLeaguesUseCase(currentApiPage, pageSize).collect { res ->
                    when (res) {
                        is Resource.Success -> {
                            val updatedItems = items.toMutableList()
                            updatedItems.addAll(res.data.map { it.toPresentationModel() })
                            items = updatedItems
                            displayItemsForCurrentLocalPage(updatePage = false)
                            isLastPage = res.data.size < pageSize
                            currentApiPage++
                        }
                        is Resource.Error -> updateErrorMessage(res.errorMessage)
                        is Resource.Loading -> loading(res.loading)
                    }
                }
            } else {
                displayItemsForCurrentLocalPage(updatePage = false)
            }
        }
    }

    private fun displayItemsForCurrentLocalPage(updatePage: Boolean = true) {
        val startIndex = max(0, (currentLocalPage - 1) * localItemsPerPage)
        val endIndex = min(startIndex + localItemsPerPage, items.size)
        val itemsToDisplay = items.subList(startIndex, endIndex)

        _homeState.update { it.copy(leagues = itemsToDisplay, isLoading = false) }

        if (updatePage) {
            currentLocalPage++
        }
    }

    private fun loadNextPage() {
        if (currentLocalPage * localItemsPerPage < items.size) {
            currentLocalPage++
            displayItemsForCurrentLocalPage(updatePage = false)
        } else if (!isLastPage) {
            fetchLeagues()
        } else {
            updateErrorMessage("You have reached the end!")
        }
    }

    private fun loadPreviousPage() {
        if (currentLocalPage > 1) {
            currentLocalPage = max(1, currentLocalPage - 1)
            displayItemsForCurrentLocalPage(updatePage = false)
        } else {
            updateErrorMessage("You are at the beginning!")
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getUserUseCase.getCurrentUserUseCase().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        updateErrorMessage(res.errorMessage)
                    }

                    is Resource.Loading -> loading(res.loading)

                    is Resource.Success -> {
                        _homeState.update {
                            it.copy(
                                user = res.data.toPresenter(),
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun saveFavouriteLeague(league: League) {
        viewModelScope.launch {
            leaguesUseCase.getSaveFavouriteLeagues(league.toDomain()).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        updateErrorMessage(resource.errorMessage)
                    }

                    is Resource.Loading -> loading(resource.loading)

                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                errorMessage = resource.data,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _homeState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _homeState.update { it.copy(errorMessage = message) }

    private fun updateNavigationEvent(events: HomeNavigationEvents) {
        viewModelScope.launch {
            _uiEvent.emit(events)
        }
    }
}