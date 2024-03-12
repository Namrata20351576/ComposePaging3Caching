package com.namrata.skosystems.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertAll(beers: List<UserEntity>)

    @Query("SELECT * FROM userentity")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM userentity")
    suspend fun clearAll()
}