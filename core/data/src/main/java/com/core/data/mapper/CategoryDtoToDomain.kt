package com.core.data.mapper

import com.core.data.model.CategoryDto
import com.core.domain.model.GetCategory

fun CategoryDto.toDomainModel() = GetCategory(
    creationAt = creationAt,
    id = id,
    image = image,
    name = name,
    updatedAt = updatedAt
)