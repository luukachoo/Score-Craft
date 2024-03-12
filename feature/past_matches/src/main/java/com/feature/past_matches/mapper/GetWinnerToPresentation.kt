package com.feature.past_matches.mapper

import com.core.domain.model.matches.past.GetWinner
import com.feature.past_matches.model.Winner

fun GetWinner.toPresentationModel() = Winner(acronym = acronym, winnerId = winnerId, name = name)