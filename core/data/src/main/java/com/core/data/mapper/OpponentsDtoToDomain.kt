package com.core.data.mapper

import com.core.data.model.OpponentWrapperDto
import com.core.domain.model.GetOpponentWrapper

fun OpponentWrapperDto.OpponentDto.toDomainModel() = GetOpponentWrapper.GetOpponent(
    id = id,
    imageUrl = imageUrl,
    name = name,
    slug = slug
)

fun OpponentWrapperDto.toDomainModel() = GetOpponentWrapper(opponent = opponent.toDomainModel())