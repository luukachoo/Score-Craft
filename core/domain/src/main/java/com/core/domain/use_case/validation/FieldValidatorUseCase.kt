package com.core.domain.use_case.validation

import javax.inject.Inject

class FieldValidatorUseCase @Inject constructor() {
    operator fun invoke(field: List<String>): Boolean = field.all { it.isNotBlank() }
}