package com.example.myphotos

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyPhotosApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyPhotosApplication
    }
}