package com.nams.mvvmretrofitdaggercompose

import com.namrata.skosystems.data.remote.UserApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserAPITest {

    lateinit var mockWebServer:MockWebServer
    lateinit var userApi: UserApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        userApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(UserApi::class.java)
    }

    @Test
    fun testEmptyUser() = runTest()
    {
        val mockResponse = MockResponse()
            mockResponse.setBody("[]")
            mockWebServer.enqueue(mockResponse)
            val response = userApi.getUsers(3)
            mockWebServer.takeRequest()
            Assert.assertEquals(true, response.data.isEmpty())
    }

    @Test
    fun testWithUser() = runTest()
    {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)
        val response = userApi.getUsers(1)
        mockWebServer.takeRequest()
        Assert.assertEquals(true, response.data!!.isNotEmpty())
        Assert.assertEquals(6, response.data.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}