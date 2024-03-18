package com.feature.match.mapper.match

import com.core.domain.model.match.GetWinner

fun GetWinner.toPresentationModel() =
    com.feature.match.model.match.Winner(acronym = acronym, winnerId = winnerId, name = name)