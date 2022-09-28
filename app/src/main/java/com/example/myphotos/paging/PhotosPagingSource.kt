package com.example.myphotos.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myphotos.api.ApiService
import com.example.myphotos.entity.MyPhoto
import kotlinx.coroutines.delay

private const val STARTING_PAGE = 1

class PhotosPagingSource(private val apiService: ApiService) : PagingSource<Int, MyPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, MyPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyPhoto> {
        val page = params.key ?: STARTING_PAGE
        return try {
            Log.d("IVONA", "page {$page} size {${params.loadSize}}")
            delay(5000) // for testing
            val response = apiService.getPhotos(page, params.loadSize)
            Log.d("IVONA", "page Loaded {$page} size {${params.loadSize}}")
            LoadResult.Page(
                data = response.body() ?: ArrayList(),
                prevKey = if (page == STARTING_PAGE) null else page.minus(1),
                nextKey = if (response.body()!!.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}