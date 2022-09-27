package com.example.myphotos.ui.photos

import androidx.lifecycle.*
import androidx.paging.*
import com.example.myphotos.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosModel @Inject constructor(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    val pagePhotos = photosRepository.getPhotos().cachedIn(viewModelScope)
}