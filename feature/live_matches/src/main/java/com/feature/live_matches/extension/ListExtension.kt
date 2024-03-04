package com.feature.live_matches.extension

import com.core.common.extension.getStreamPreview
import com.feature.live_matches.test_model.MatchListItem

fun List<MatchListItem.Match.Streams?>?.checkPreview(): String? {
    this?.let {
        return if (this.isNotEmpty()) this.last()?.embedUrl?.getStreamPreview("low")
        else null
    }
    return null
}