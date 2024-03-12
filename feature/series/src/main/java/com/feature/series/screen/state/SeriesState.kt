package com.feature.series.screen.state

import com.feature.series.screen.model.Series

data class SeriesState(
    val series: List<Series>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)