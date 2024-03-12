package com.namrata.skosystems.data.remote

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.namrata.skosystems.data.local.UserEntity
import com.namrata.skosystems.data.mappers.toUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import androidx.paging.Pager
import kotlinx.coroutines.CoroutineScope

class DataPoller(
    val repository: UserRepository,
    val dispatcher: CoroutineDispatcher): Poller {

    override fun poll(delay: Long): Flow<UserDTO> {
        return channelFlow {
            var i = 1
            while (!isClosedForSend && i<6) {
                delay(delay)
                    val either = repository.getUsers(i)
//                either.fold({ exception ->
//                    print("Failed to get data")
//                }, {
//                    send(data)
//                })
                    send(either)
                    if(i==5){
                        i=1
                    } else {
                        i++
                    }

            }
        }.flowOn(dispatcher)
    }
    override fun close() {
        dispatcher.cancel()
    }

}