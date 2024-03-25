package com.core.domain.use_case.validation

import javax.inject.Inject

data class ValidationUseCase @Inject constructor(
    val fieldValidatorUseCase: FieldValidatorUseCase,
    val confirmPasswordValidatorUseCase: ConfirmPasswordValidatorUseCase
)