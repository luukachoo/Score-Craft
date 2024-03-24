package com.feature.home.event

import com.feature.home.model.League

sealed class HomeFragmentEvent {
    data object FetchCategories : HomeFragmentEvent()
    data object ResetErrorMessage : HomeFragmentEvent()
    data object GetCurrentUser : HomeFragmentEvent()
    data class OnLeagueClick(val slug: String) : HomeFragmentEvent()
    data object OnProfileClick : HomeFragmentEvent()
    data object LoadNextPage : HomeFragmentEvent()
    data object LoadPreviousPage : HomeFragmentEvent()
    data class SaveFavouriteLeague(val league: League) : HomeFragmentEvent()
}