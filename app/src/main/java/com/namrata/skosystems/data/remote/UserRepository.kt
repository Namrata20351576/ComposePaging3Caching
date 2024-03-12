package com.namrata.skosystems.data.remote

import android.annotation.SuppressLint
import javax.inject.Inject

class UserRepository  @Inject constructor(private val userApi: UserApi) {

        @SuppressLint("SuspiciousIndentation")
        suspend fun getUsers(page:Int): UserDTO
        {
            val result = userApi.getUsers(page)
            return result
        }
    }