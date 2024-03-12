package com.namrata.skosystems.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.namrata.skosystems.data.local.UserDatabase
import com.namrata.skosystems.data.local.UserEntity
import com.namrata.skosystems.data.remote.UserApi
import com.namrata.skosystems.data.remote.UserRemoteMediator
import com.namrata.skosystems.data.remote.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserPager(userDb: UserDatabase, userApi: UserApi): Pager<Int, UserEntity> {
        return Pager(
            config = PagingConfig(pageSize = 12),
            remoteMediator = UserRemoteMediator(
                userDb = userDb,
                repository = UserRepository(userApi =userApi)
            ),
            pagingSourceFactory = {
                userDb.dao.pagingSource()
            }
        )
    }

}