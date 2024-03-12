package com.namrata.skosystems.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.namrata.skosystems.data.local.UserDatabase
import com.namrata.skosystems.data.local.UserEntity
import com.namrata.skosystems.data.mappers.toUser
import com.namrata.skosystems.data.mappers.toUserEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator  @Inject constructor(
    private val userDb: UserDatabase,
    private val repository: UserRepository
): RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val users = repository.getUsers(
                page = loadKey,
            )

            println("Data Received --- ${users.toString()}")

            userDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    userDb.dao.clearAll()
                }
                val userEntities = users.data.map { it.toUserEntity() }
                userDb.dao.upsertAll(userEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = users.data.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}