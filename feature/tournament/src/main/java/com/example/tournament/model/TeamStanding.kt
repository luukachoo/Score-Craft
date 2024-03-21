package com.example.tournament.model

import com.core.common.resource.Recyclable
import com.core.domain.model.league.GetTeam

data class TeamStanding(
    val rank: Int,
    val team: GetTeam
) : Recyclable<TeamStanding>() {
    override val uniqueValue: TeamStanding
        get() = this
}
