package com.namrata.skosystems.domain

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String?
)
