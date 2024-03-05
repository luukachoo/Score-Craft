package com.core.data.mapper

import com.core.data.model.ResultsDto
import com.core.domain.model.GetResults

fun ResultsDto.toDomainModel() = GetResults(firstTeam = firstTeam.toDomainModel(), secondTeam = secondTeam.toDomainModel())