package com.example.myphotos.ui.photos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myphotos.entity.MyPhoto
import com.example.myphotos.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosModel @Inject constructor(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    private val _photos = MutableLiveData<List<MyPhoto>>()
    val photos: LiveData<List<MyPhoto>>
        get() = _photos

    init {
        getPhotos()
    }

    private fun getPhotos() {
        Log.d("IVONA", " getphotos")
        viewModelScope.launch {
            _photos.value = photosRepository.fetchPhotos()
            _photos.value?.let {
                for (photo in it) {
                    Log.d(
                        "IVONA",
                        " id {${photo.id}} {${photo.author}} {${photo.url}} {${photo.downloadUrl}}"
                    )
                }
            }
        }
    }

}