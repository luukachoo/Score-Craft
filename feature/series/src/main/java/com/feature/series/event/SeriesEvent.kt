package com.feature.series.event

sealed class SeriesEvent {
    data object ResetErrorMessage : SeriesEvent()
    data class FetchSeriesBySlug(val slug: String) : SeriesEvent()
    data class NavigateToTournaments(val slug: String) : SeriesEvent()
    data object NavigateToHome : SeriesEvent()
}