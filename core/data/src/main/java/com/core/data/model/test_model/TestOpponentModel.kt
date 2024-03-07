package com.core.data.model.test_model

data class TestOpponentModel(
    val opponents: List<Opponent>,
    val winner_id: Any?
) {
    data class Opponent(
        val opponent: Opponent,
        val type: String
    ) {
        data class Opponent(
            val acronym: String,
            val id: Int,
            val image_url: String,
            val location: String,
            val modified_at: String,
            val name: String,
            val slug: String
        )
    }
}