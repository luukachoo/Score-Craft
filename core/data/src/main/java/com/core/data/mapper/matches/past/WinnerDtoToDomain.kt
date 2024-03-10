package com.core.data.mapper.matches.past

import com.core.data.model.matches.past.WinnerDto
import com.core.domain.model.matches.past.GetWinner

fun WinnerDto.toDomainModel() = GetWinner(acronym = acronym, winnerId = winnerId)