package com.core.domain.use_case.leagues

import javax.inject.Inject

class LeagueUseCase @Inject constructor(
    val getLeaguesUseCase: GetLeaguesUseCase,
    val getSaveFavouriteLeagues: GetSaveFavouriteLeagues,
    val getFavouriteLeaguesUseCase: GetFavouriteLeaguesUseCase,
    val getFetchFriendFavouriteLeagueUseCase: GetFetchFriendFavouriteLeagueUseCase
)