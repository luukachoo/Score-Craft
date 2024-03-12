package com.core.domain.use_case.series

import com.core.domain.repository.series.SeriesRepository
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val seriesRepository: SeriesRepository) {
    suspend operator fun invoke(slug: String) =
        seriesRepository.getSeriesBySlug(slug = slug)
}