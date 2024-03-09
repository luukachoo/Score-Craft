package com.core.data.mapper.matches

import com.core.data.model.matches.live.OpponentWrapperDto
import com.core.domain.model.matches.live.GetOpponentWrapper

fun OpponentWrapperDto.OpponentDto.toDomainModel() = GetOpponentWrapper.GetOpponent(
    id = id,
    imageUrl = imageUrl,
    name = name,
    slug = slug
)

fun OpponentWrapperDto.toDomainModel() = GetOpponentWrapper(opponent = opponent.toDomainModel())