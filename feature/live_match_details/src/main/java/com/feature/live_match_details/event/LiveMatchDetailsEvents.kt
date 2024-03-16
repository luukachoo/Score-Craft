package com.feature.live_match_details.event

sealed class LiveMatchDetailsEvents {
    data class FetchMatchDetailsById(val matchId: Int) : LiveMatchDetailsEvents()
    data class FetchTeamMembersByMatchId(val matchId: Int) : LiveMatchDetailsEvents()
    data object ResetErrorMessage : LiveMatchDetailsEvents()
    data object BackButtonClick : LiveMatchDetailsEvents()
}