package com.core.common.extension

import android.net.Uri
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions

internal fun buildDeepLink(destination: DeepLinkDestination) =
    NavDeepLinkRequest.Builder
        .fromUri(destination.address.toUri())
        .build()

fun NavController.deepLinkNavigateTo(
    deeplinkDestination: DeepLinkDestination,
    popUpTo: Boolean = false
) {
    val builder = NavOptions.Builder()
    if (popUpTo) {
        builder.setPopUpTo(graph.id, false)
    }

    navigate(
        buildDeepLink(deeplinkDestination),
        builder.build()
    )
}

sealed class DeepLinkDestination(val address: String) {
    class LiveMatchDetails(matchId: Int) :
        DeepLinkDestination("market-mingle://feature.fragment_live_match_details/fragment_live_match_details?matchId=${matchId}")
    class Login(email: String?, password: String?) :
        DeepLinkDestination("market-mingle://feature.login/fragment_login?email=${email}&password=${password}")
    class ProfileWithImageUri(imageUri: Uri) :
        DeepLinkDestination("market-mingle://feature.profile/fragment_profile?imageUri=$imageUri")
    class Series(slug: String) :
        DeepLinkDestination("market-mingle://feature.series/fragment_series?slug=${slug}")
    class Tournament(slug: String) :
        DeepLinkDestination("market-mingle://feature.tournament/fragment_tournament?slug=${slug}")
    class TournamentDetails(slug: String) :
        DeepLinkDestination("market-mingle://feature.tournament/fragment_tournament_details?slug=${slug}")
    data object Home : DeepLinkDestination("market-mingle://feature.home/fragment_home")
    data object Welcome : DeepLinkDestination("market-mingle://feature.welcome/fragment_welcome")
    data object LoginWithoutArgument : DeepLinkDestination("market-mingle://feature.login/fragment_login")
    data object Register : DeepLinkDestination("market-mingle://feature.register/fragment_register")
    data object ForgotPassword : DeepLinkDestination("market-mingle://feature.forgot_password/fragment_forgot_password")
    data object BottomSheet : DeepLinkDestination("market-mingle://feature.image_bottom_sheet/fragment_image_bottom_sheet")
    data object Profile : DeepLinkDestination("market-mingle://feature.profile/fragment_profile")
    class Message(friendId: String) : DeepLinkDestination("market-mingle://feature.message/fragment_message?friendId=${friendId}")
    data object FriendRequest : DeepLinkDestination("market-mingle://feature.friend_request/fragment_friend_request")
}