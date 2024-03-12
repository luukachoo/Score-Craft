package com.core.domain.use_case.auth

import com.core.domain.repository.auth.AuthRepository
import javax.inject.Inject

class GetSaveFavouriteLeagues @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(leagueSlug: String) = authRepository.saveFavouriteLeagues(leagueSlug)
}