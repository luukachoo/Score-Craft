package com.feature.home.event

sealed class HomeFragmentEvent {
    data object FetchCategories : HomeFragmentEvent()
    data object FetchProducts : HomeFragmentEvent()
    data object EditTextClick : HomeFragmentEvent()
    data object ResetErrorMessage : HomeFragmentEvent()
    data class ItemClick(val id: Int) : HomeFragmentEvent()
}