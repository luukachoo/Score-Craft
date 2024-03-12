package com.feature.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.auth.GetAuthUseCase
import com.core.domain.use_case.favourite_leagues.GetFavouriteLeaguesUseCase
import com.core.domain.use_case.leagues.GetLeaguesUseCase
import com.feature.home.event.HomeFragmentEvent
import com.feature.home.event.HomeNavigationEvents
import com.feature.home.mapper.auth.toPresenter
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
    private val getLeaguesUseCase: GetLeaguesUseCase,
    private val getAuthUseCase: GetAuthUseCase,
    private val getFavouriteLeaguesUseCase: GetFavouriteLeaguesUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState get() = _homeState

    private val _homeUiEvent = MutableSharedFlow<HomeNavigationEvents>()
    val homeUiEvent: SharedFlow<HomeNavigationEvents> get() = _homeUiEvent

    private var currentApiPage = 1
    private var currentLocalPage = 1
    private val pageSize = 100
    private val localItemsPerPage = 20
    private var items = mutableListOf<League>()
    private var isLastPage = false


    fun onEvent(event: HomeFragmentEvent) {
        viewModelScope.launch {
            when (event) {
                HomeFragmentEvent.EditTextClick -> updateNavigationEvent(HomeNavigationEvents.NavigateToSearch)
                HomeFragmentEvent.FetchCategories -> fetchLeagues()
                HomeFragmentEvent.FetchProducts -> Unit
                is HomeFragmentEvent.ItemClick -> updateNavigationEvent(
                    HomeNavigationEvents.NavigateToDetails(
                        event.id
                    )
                )

                HomeFragmentEvent.ResetErrorMessage -> updateErrorMessage(message = null)
                HomeFragmentEvent.GetCurrentUser -> getCurrentUser()
                is HomeFragmentEvent.FetchUserProfileImage -> fetchUserProfileImage(event.userId)
                HomeFragmentEvent.LoadNextPage -> loadNextPage()
                HomeFragmentEvent.LoadPreviousPage -> loadPreviousPage()
                is HomeFragmentEvent.SaveFavouriteLeague -> saveFavouriteLeague(event.leagueSlug)
            }
        }
    }

    private fun fetchLeagues() {
        viewModelScope.launch {
            if (isLastPage) {
                return@launch
            }

            try {
                val localPagesDisplayed =
                    (items.size / localItemsPerPage) - (currentApiPage - 1) * (pageSize / localItemsPerPage)

                if (currentLocalPage > localPagesDisplayed) {
                    getLeaguesUseCase(currentApiPage, pageSize).collect { res ->
                        when (res) {
                            is Resource.Success -> {
                                val updatedItems = items.toMutableList()
                                updatedItems.addAll(res.data.map { it.toPresentationModel() })
                                items = updatedItems
                                displayItemsForCurrentLocalPage()
                                isLastPage = res.data.size < pageSize
                                currentApiPage++
                            }

                            is Resource.Error -> updateErrorMessage(res.errorMessage)
                            is Resource.Loading -> loading(res.loading)
                        }
                    }
                } else {
                    displayItemsForCurrentLocalPage()
                }
            } catch (e: Exception) {
                updateErrorMessage(e.message)
            }
        }
    }

    private fun displayItemsForCurrentLocalPage() {
        val startIndex =
            max(0, (currentLocalPage - 1) * localItemsPerPage)
        val endIndex = min(
            startIndex + localItemsPerPage,
            items.size
        )
        val itemsToDisplay = items.subList(startIndex, endIndex)
        _homeState.update { it.copy(leagues = itemsToDisplay, isLoading = false) }
        currentLocalPage++
    }

    private fun loadNextPage() {
        if (currentLocalPage * localItemsPerPage < items.size) {
            displayItemsForCurrentLocalPage()
        } else if (!isLastPage) {
            fetchLeagues()
        } else {
            updateErrorMessage("You have reached the end!")
        }
    }

    private fun loadPreviousPage() {
        if (currentLocalPage > 1) {
            currentLocalPage =
                max(1, currentLocalPage - 2)
            displayItemsForCurrentLocalPage()
        } else if (currentApiPage > 1) {
            currentApiPage--
            fetchLeagues()
            currentLocalPage = max(
                1,
                (pageSize / localItemsPerPage) - 1
            )
        } else {
            updateErrorMessage("You are at the beginning!")
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getAuthUseCase.getCurrentUserUseCase().collect { res ->
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

    private fun fetchUserProfileImage(userId: String) {
        viewModelScope.launch {
            getAuthUseCase.getUserProfileImageUseCase(userId).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                imageFetched = true
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }

                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                imageUri = resource.data,
                                isLoading = false,
                                imageFetched = true
                            )
                        }
                    }
                }
            }
        }
    }

    private fun saveFavouriteLeague(leagueSlug: String) {
        viewModelScope.launch {
            getAuthUseCase.getSaveFavouriteLeagues(leagueSlug).collect { resource ->
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

    private suspend fun updateNavigationEvent(events: HomeNavigationEvents) =
        _homeUiEvent.emit(events)
}