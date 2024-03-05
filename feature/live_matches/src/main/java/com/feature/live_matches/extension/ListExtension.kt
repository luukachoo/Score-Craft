package com.feature.live_matches.extension

import com.core.common.extension.getStreamPreview
import com.feature.live_matches.model.Match

fun List<Match.Stream?>?.checkForPreview(): String? {
    this?.let {
        return if (this.isNotEmpty()) this.last()?.embedUrl?.getStreamPreview("")
        else null
    }
    return null
}