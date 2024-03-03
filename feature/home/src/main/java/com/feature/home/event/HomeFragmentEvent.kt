package com.feature.home.event

sealed class HomeFragmentEvent {
    data object FetchCategories : HomeFragmentEvent()
    data object ResetErrorMessage : HomeFragmentEvent()
    data class ItemClick(val slug: String) : HomeFragmentEvent()
}