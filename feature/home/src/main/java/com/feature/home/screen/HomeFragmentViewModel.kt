package com.feature.home.screen

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.resource.Resource
import com.core.domain.use_case.GetLeaguesUseCase
import com.core.domain.use_case.auth.GetAuthUseCase
import com.feature.home.event.HomeFragmentEvent
import com.feature.home.event.HomeNavigationEvents
import com.feature.home.mapper.auth.toPresenter
import com.feature.home.mapper.toPresentationModel
import com.feature.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getLeaguesUseCase: GetLeaguesUseCase,
    private val getAuthUseCase: GetAuthUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState get() = _homeState

    private val _homeUiEvent = MutableSharedFlow<HomeNavigationEvents>()
    val homeUiEvent: SharedFlow<HomeNavigationEvents> get() = _homeUiEvent

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
            }
        }
    }

    private fun fetchLeagues() {
        viewModelScope.launch {
            getLeaguesUseCase().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        updateErrorMessage(res.errorMessage)
                        d("ViewModelHttpError", res.errorMessage)
                    }

                    is Resource.Loading -> loading(res.loading)

                    is Resource.Success -> {
                        _homeState.update {
                            it.copy(
                                categories = res.data.map { getLeague -> getLeague.toPresentationModel() },
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
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

//    private fun fetchProducts() {
//        viewModelScope.launch {
//            getProductsUseCase().collect { res ->
//                when (res) {
//                    is Resource.Error -> {
//                        updateErrorMessage(res.errorMessage)
//                        d("ViewModelHttpError", res.errorMessage)
//                    }
//
//                    is Resource.Loading -> loading(res.loading)
//                    is Resource.Success -> {
//                        _homeState.update {
//                            it.copy(
//                                products = res.data.map { getProduct -> getProduct.toPresentationModel() },
//                                isLoading = false,
//                                errorMessage = null
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }

    private fun loading(isLoading: Boolean) =
        _homeState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _homeState.update { it.copy(errorMessage = message) }

    private suspend fun updateNavigationEvent(events: HomeNavigationEvents) =
        _homeUiEvent.emit(events)
}