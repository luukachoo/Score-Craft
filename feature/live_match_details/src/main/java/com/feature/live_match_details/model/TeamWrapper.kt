package com.feature.live_match_details.model

data class TeamWrapper(
    val opponents: List<Team>
) {
    data class Team(
        val id: Int,
        val name: String,
        val slug: String,
        val players: List<Player>
    ) {
        data class Player(
            val active: Boolean,
            val age: Int?,
            val birthday: String?,
            val firstName: String,
            val id: Int,
            val imageUrl: String?,
            val lastName: String,
            val name: String,
            val nationality: String,
            val role: String?,
            val slug: String
        )
    }
}
