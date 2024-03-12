package com.namrata.skosystems.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String?
)