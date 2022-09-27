package com.example.myphotos.api

import com.example.myphotos.entity.MyPhoto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v2/list")
    suspend fun getPhotos(): Response<List<MyPhoto>>
}