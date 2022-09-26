package com.example.myphotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myphotos.ui.photos.PhotosFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PhotosFragment.newInstance())
                .commit()
        }
    }
}