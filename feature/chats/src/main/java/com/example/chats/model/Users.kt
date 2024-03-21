package com.example.chats.model

import com.core.common.resource.Recyclable

data class Users(
    val userId: String,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val avatar: String
) : Recyclable<Users>() {
    override val uniqueValue: Users
        get() = this
}
