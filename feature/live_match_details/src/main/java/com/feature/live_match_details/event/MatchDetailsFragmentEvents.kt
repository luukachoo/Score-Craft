package com.feature.live_match_details.event

sealed class MatchDetailsFragmentEvents {
    data class FetchMatchById(val matchId: Int) : MatchDetailsFragmentEvents()

    //    data class FetchMatchOpponents(val matchId: Int) : MatchDetailsFragmentEvents()
    data object BackButtonClick : MatchDetailsFragmentEvents()
    data object ResetErrorMessage : MatchDetailsFragmentEvents()
}