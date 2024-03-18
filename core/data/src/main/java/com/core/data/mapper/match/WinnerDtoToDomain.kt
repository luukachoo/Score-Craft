package com.core.data.mapper.match

import com.core.data.model.match.WinnerDto
import com.core.domain.model.match.GetWinner

fun WinnerDto.toDomainModel() = GetWinner(acronym = acronym, winnerId = winnerId, name = name)