package com.feature.home.event

sealed interface HomeNavigationEvents {
    data class NavigateToSeries(val slug: String) : HomeNavigationEvents
    data object NavigateToProfile : HomeNavigationEvents
}