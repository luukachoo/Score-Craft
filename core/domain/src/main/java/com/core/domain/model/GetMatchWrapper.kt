package com.core.domain.model

data class GetMatchWrapper(
    val match: GetMatch
) {
    data class GetMatch(
        val slug: String,
        val status: String,
        val originalScheduledAt: String,
        val id: Int,
        val name: String,
        val detailedStats: Boolean,
        val scheduledAt: String,
        val beginAt: String,
        val videogame: GetVideoGame,
        val videoGameTitle: Any?,
        val forfeit: Boolean,
        val streamsList: List<GetStream>
    ) {
        data class GetVideoGame(
            val id: Int,
            val name: String,
            val slug: String
        )

        data class GetLeague(
            val id: Int,
            val imageUrl: String?,
            val modifiedAt: String,
            val name: String,
            val slug: String,
            val url: String?
        )

        data class GetWinner(
            val id: Int?,
            val type: String
        )
    }
}