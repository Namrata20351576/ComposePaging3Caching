package com.namrata.skosystems.data.remote

import java.util.concurrent.Flow


interface Poller {
    fun poll(delay: Long): kotlinx.coroutines.flow.Flow<UserDTO>
    fun close()
}