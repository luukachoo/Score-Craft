package com.core.data.mapper

//fun GetMatch.toDomainModel() = GetMatch(
//    endAt = endAt,
//    gameDtos = gameDtoDtos.map { it.toDomainModel() },
//    id = id,
//    league = league.toDomainModel(),
//    leagueId = leagueId,
//    matchType = matchType,
//    modifiedAt = modifiedAt,
//    name = name,
//    numberOfGames = numberOfGames,
//    getOpponents = opponentsDtos.map { it.toDomainModel() },
//    originalScheduledAt = originalScheduledAt,
//    rescheduled = rescheduled,
//    getResults = resultDtos.map { it.toDomainModel() },
//    scheduledAt = scheduledAt,
//    slug = slug,
//    status = status,
//    streamsList = streamsList.map { it.toDomainModel() },
//    videogame = videogame.toDomainModel(),
//    winner = winner,
//    winnerId = winnerId,
//    winnerType = winnerType
//)
//
//fun GetMatch.GetGame.toDomainModel() = GetMatch.GetGame(
//    beginAt = beginAt,
//    complete = complete,
//    detailedStats = detailedStats,
//    endAt = endAt,
//    finished = finished,
//    id = id,
//    matchId = matchId,
//    position = position,
//    status = status,
//    winner = winner.toDomainModel(),
//    winnerType = winnerType
//)
//
//
//fun GetMatch.StreamsDto.toDomainModel() = GetMatch.GetStreams(
//    embedUrl = embedUrl,
//    language = language,
//    main = main,
//    official = official,
//    rawUrl = rawUrl
//)
//
//fun GetMatch.GetResult.toDomainModel() = GetMatch.GetResult(score = score, teamId = teamId)
//
//fun GetMatch.GetGame.WinnerDto.toDomainModel() = GetMatch.GetGame.GetWinner(id = id, type = type)
//
//fun GetMatch.OpponentDto.toDomainModel() =
//    GetMatch.GetOpponent(opponent = opponent.toDomainModel(), type = type)
