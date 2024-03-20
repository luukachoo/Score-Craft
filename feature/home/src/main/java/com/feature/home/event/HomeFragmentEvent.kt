package com.feature.home.event

sealed class HomeFragmentEvent {
    data object FetchCategories : HomeFragmentEvent()
    data object FetchProducts : HomeFragmentEvent()
    data object ResetErrorMessage : HomeFragmentEvent()
    data object GetCurrentUser : HomeFragmentEvent()
    data class ItemClick(val id: Int) : HomeFragmentEvent()
//    data class FetchUserProfileImage(val userId: String) : HomeFragmentEvent()
    data object LoadNextPage : HomeFragmentEvent()
    data object LoadPreviousPage : HomeFragmentEvent()
    data class SaveFavouriteLeague(val leagueSlug: String) : HomeFragmentEvent()
}