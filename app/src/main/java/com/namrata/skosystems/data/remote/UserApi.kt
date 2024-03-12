package com.namrata.skosystems.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): UserDTO

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }
}