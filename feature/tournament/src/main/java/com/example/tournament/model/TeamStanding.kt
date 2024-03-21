package com.example.tournament.model

import com.core.common.resource.Recyclable

data class TeamStanding(
    val rank: Int,
    val team: Team
) : Recyclable<TeamStanding>() {
    override val uniqueValue: TeamStanding
        get() = this
}