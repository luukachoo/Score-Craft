package com.feature.match.extension

import com.core.common.extension.getStreamPreview
import com.feature.match.helper.Quality
import com.feature.match.model.match.Stream

fun List<Stream?>?.checkForPreview(): String? {
    this?.let {
        return if (this.isNotEmpty()) this.last()?.embedUrl?.getStreamPreview(Quality.LOW)
        else null
    }
    return null
}