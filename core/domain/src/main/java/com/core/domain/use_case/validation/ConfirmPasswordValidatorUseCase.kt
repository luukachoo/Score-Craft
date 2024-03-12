package com.core.domain.use_case.validation

import javax.inject.Inject

class ConfirmPasswordValidatorUseCase @Inject constructor() {
    operator fun invoke(passwordOne: String, passwordTwo: String): Boolean {
        return passwordOne == passwordTwo
    }
}