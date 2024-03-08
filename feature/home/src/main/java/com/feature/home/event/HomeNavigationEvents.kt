package com.feature.home.event

sealed interface HomeNavigationEvents {
    data class NavigateToDetails(val slug: String) : HomeNavigationEvents
}