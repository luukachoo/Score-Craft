package com.core.data.model.test_model

data class OpponentWrapperDto(
    val opponentType: String,
    val opponents: List<Opponent>
) {
    data class Opponent(
        val acronym: String,
        val current_videogame: CurrentVideogame,
        val id: Int,
        val image_url: String,
        val location: String,
        val modified_at: String,
        val name: String,
        val players: List<Player>,
        val slug: String
    ) {
        data class CurrentVideogame(
            val id: Int,
            val name: String,
            val slug: String
        )

        data class Player(
            val active: Boolean,
            val age: Int,
            val birthday: String,
            val first_name: String,
            val id: Int,
            val image_url: String,
            val last_name: String,
            val modified_at: String,
            val name: String,
            val nationality: String,
            val role: String,
            val slug: String
        )
    }
}