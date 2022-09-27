package com.example.myphotos.repository

import android.util.Log
import com.example.myphotos.api.ApiService
import com.example.myphotos.entity.MyPhoto
import retrofit2.Response

class PhotosRepository(
    private val apiService: ApiService
) {

    private suspend fun getPhotosApi() = apiService.getPhotos()

    suspend fun fetchPhotos(): List<MyPhoto>? {
        Log.d("IVONA", "fetchPhotos 1")
        val photosResponse: Response<List<MyPhoto>>
        try {
            photosResponse = getPhotosApi()
        } catch (e: Exception) {
            Log.d("IVONA", "fetchPhotos 2 " + e.message)
            e.printStackTrace()
            return null
        }

        if (photosResponse.isSuccessful) {
            Log.d("IVONA", "fetchPhotos 3")
            return photosResponse.body()
        }

        Log.d("IVONA", "fetchPhotos 4")
        return null
    }
}