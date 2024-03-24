package com.core.data.mapper.league

import com.core.data.model.league.TeamStandingDto
import com.core.domain.model.league.GetTeamStanding

fun TeamStandingDto.toDomainModel() = GetTeamStanding(rank = rank, team = team.toDomainModel())