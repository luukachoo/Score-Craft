package com.feature.match.mapper.match

import com.core.domain.model.match.GetWinner
import com.feature.match.model.match.Winner

fun GetWinner.toPresentationModel() = Winner(acronym = acronym, winnerId = winnerId, name = name)