package com.khrlanamm.suitmediamd.data.remote.retrofit


import com.khrlanamm.suitmediamd.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<UserResponse>
}