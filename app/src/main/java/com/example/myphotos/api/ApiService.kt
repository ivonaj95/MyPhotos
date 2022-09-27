package com.example.myphotos.api

import com.example.myphotos.entity.MyPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/list")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<MyPhoto>>

}