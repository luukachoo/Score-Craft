package com.feature.home.event

sealed interface HomeNavigationEvents {
    data class NavigateToDetails(val id: Int) : HomeNavigationEvents
    data object NavigateToSearch : HomeNavigationEvents
}