package com.core.data.mapper.match

import com.core.data.model.match.OpponentWrapperDto
import com.core.domain.model.match.GetOpponentWrapper

fun OpponentWrapperDto.OpponentDto.toDomainModel() = GetOpponentWrapper.GetOpponent(
    id = id,
    imageUrl = imageUrl,
    name = name,
    slug = slug
)

fun OpponentWrapperDto.toDomainModel() = GetOpponentWrapper(opponent = opponent.toDomainModel())