package com.core.domain.model.live_matches

data class GetTeamWrapper(
    val opponents: List<GetTeam>
) {
    data class GetTeam(
        val id: Int,
        val name: String,
        val slug: String,
        val players: List<GetPlayer>
    ) {
        data class GetPlayer(
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


