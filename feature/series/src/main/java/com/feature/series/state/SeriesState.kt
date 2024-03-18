package com.feature.series.state

import com.feature.series.model.Series

data class SeriesState(
    val series: List<Series>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)