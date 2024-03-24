package com.core.data.mapper.league

import com.core.data.mapper.match.toDomainModel
import com.core.data.model.league.ResultDto
import com.core.data.model.league.TeamDto
import com.core.data.model.league.TournamentDto
import com.core.data.model.match.MatchDto
import com.core.domain.model.league.GetResult
import com.core.domain.model.league.GetTeam
import com.core.domain.model.league.GetTournament
import com.core.domain.model.match.GetMatch

fun TournamentDto.toDomainModel() = GetTournament(
    beginAt = beginAt ?: "We do not know yet",
    id = id,
    name = name,
    prizePool = prizePool ?: "No Prize Pool",
    slug = slug,
    teams = teams.map { it.toDomainModel() },
    league = league.toDomainModel()
)

fun MatchDto.toDomainModel() = GetMatch(
    results = results.map { it.toDomainModel() },
    id = id,
    opponents = opponents.map { it.toDomainModel() },
    beginAt = beginAt,
    name = name,
    winner = winner?.toDomainModel(),
    videoGame = videoGame.toDomainModel(),
    winnerId = winnerId
)

fun TeamDto.toDomainModel() = GetTeam(
    acronym = acronym ?: "",
    id = id,
    imageUrl = imageUrl ?: "",
    location = location ?: "",
    name = name
)

fun ResultDto.toDomainModel() = GetResult(score = score, teamId = teamId)