package com.namrata.skosystems.data.mappers

import com.namrata.skosystems.data.local.UserEntity
import com.namrata.skosystems.data.remote.Data
import com.namrata.skosystems.domain.User

fun Data.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        first_name = first_name,
        last_name = last_name,
        email = email,
        avatar = avatar
    )
}


fun UserEntity.toUser(): User {
    return User(
        id = id,
        firstName = first_name,
        lastName = last_name,
        email = email,
        avatar = avatar
    )
}