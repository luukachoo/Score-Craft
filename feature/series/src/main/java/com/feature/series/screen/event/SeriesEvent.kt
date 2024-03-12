package com.feature.series.screen.event

sealed class SeriesEvent {
    data object ResetErrorMessage : SeriesEvent()
    data class FetchSeriesBySlug(val slug: String) : SeriesEvent()
    data object NavigateToHome : SeriesEvent()
}