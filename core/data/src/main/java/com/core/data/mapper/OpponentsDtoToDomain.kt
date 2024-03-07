package com.core.data.mapper

import com.core.data.model.OpponentWithTypeDto
import com.core.domain.model.GetOpponentWithType


fun OpponentWithTypeDto.toDomainModel() =
    GetOpponentWithType(opponents = opponents.map { it.toDomainModel() })

fun OpponentWithTypeDto.OpponentWrapperDto.OpponentDto.toDomainModel() =
    GetOpponentWithType.GetOpponentWrapper.GetOpponent(
        slug = slug, id = id, imageUrl = imageUrl, name = name,
    )
