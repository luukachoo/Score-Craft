package com.feature.home.mapper

import com.core.domain.model.GetCategory
import com.feature.home.model.Category

fun GetCategory.toPresentationModel() = Category(
    id = id,
    image = image,
    name = name
)