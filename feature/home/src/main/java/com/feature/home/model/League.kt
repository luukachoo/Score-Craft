package com.feature.home.model

import com.core.common.resource.Recyclable

data class League(
    val id: Int,
    val imageUrl: String?,
    val modifiedAt: String,
    val name: String,
    val getSeriesList: List<Series>,
    val slug: String,
    val url: Any?,
) : Recyclable<League>() {
    override val uniqueValue: League
        get() = this
}
