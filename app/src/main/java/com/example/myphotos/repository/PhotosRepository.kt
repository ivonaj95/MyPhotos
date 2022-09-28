package com.example.myphotos.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.myphotos.GRID_SIZE
import com.example.myphotos.api.ApiService
import com.example.myphotos.entity.MyPhoto
import com.example.myphotos.paging.PhotosPagingSource

class PhotosRepository(
    private val apiService: ApiService
) {

    fun getPhotos(): LiveData<PagingData<MyPhoto>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PhotosPagingSource(apiService) }
        ).liveData
    }

    companion object {
        const val PAGE_SIZE = 2 * GRID_SIZE
    }
}