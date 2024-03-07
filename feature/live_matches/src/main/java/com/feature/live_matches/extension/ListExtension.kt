package com.feature.live_matches.extension

import com.core.common.extension.getStreamPreview
import com.feature.live_matches.helper.Quality
import com.feature.live_matches.model.Stream

fun List<Stream?>?.checkForPreview(): String? {
    this?.let {
        return if (this.isNotEmpty()) this.last()?.embedUrl?.getStreamPreview(Quality.LOW)
        else null
    }
    return null
}