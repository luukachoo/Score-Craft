package com.feature.live_match_details.model

data class OpponentWithType(
    val opponents: List<OpponentWrapper.Opponent>,
) {
    data class OpponentWrapper(
        val opponent: Opponent
    ) {
        data class Opponent(
            val id: Int,
            val imageUrl: String,
            val name: String,
            val slug: String,
        )
    }
}


data class Player(
    val active: Boolean,
    val age: Int,
    val birthday: String,
    val firstName: String,
    val id: Int,
    val imageUrl: String,
    val lastName: String,
    val name: String,
    val nationality: String,
    val role: String,
    val slug: String
)