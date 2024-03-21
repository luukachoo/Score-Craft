package com.feature.live_match_details.model

import com.core.common.resource.Recyclable

data class Players(
    val firstTeamPlayer: TeamWrapper.Team.Player? = null,
    val secondTeamPlayer: TeamWrapper.Team.Player? = null
) : Recyclable<Players>() {
    override val uniqueValue: Players
        get() = this
}
