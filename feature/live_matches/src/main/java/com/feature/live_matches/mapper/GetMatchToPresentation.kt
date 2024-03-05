package com.feature.live_matches.mapper

import com.core.domain.model.GetMatchWrapper
import com.feature.live_matches.model.MatchWrapper

fun GetMatchWrapper.toPresentationModel() = MatchWrapper(match = match.toPresentationModel())

fun GetMatchWrapper.GetMatch.toPresentationModel() =
    MatchWrapper.Match(
        slug = slug,
        status = status,
        originalScheduledAt = originalScheduledAt,
        games = games,
        serieId = serieId,
        videoGameVersion = videoGameVersion,
        numberOfGames = numberOfGames,
        id = id,
        name = name,
        detailedStats = detailedStats,
        scheduledAt = scheduledAt,
        beginAt = beginAt,
        videogame = videogame,
        results = results.map { it.toPresentationModel() },
        videoGameTitle = videoGameTitle,
        forfeit = forfeit,
        opponents = opponents,
        streamsList = streamsList.map { it.toPresentationModel() }
    )


//fun GetMatchWrapper.GetMatch.GetLeague.toPresentationModel() = MatchWrapper.Match.League(
//    id = id, imageUrl = imageUrl, modifiedAt = modifiedAt, name = name, slug = slug, url = url
//)
//
//fun GetMatchWrapper.GetMatch.GetLeague.toDomainModel() = MatchWrapper.Match.League(
//    id = id,
//    imageUrl = imageUrl,
//    modifiedAt = modifiedAt,
//    name = name,
//    slug = slug,
//    url = url
//)

fun GetMatchWrapper.GetMatch.GetWinner.toPresentationModel() =
    MatchWrapper.Match.Winner(id = id, type = type)

