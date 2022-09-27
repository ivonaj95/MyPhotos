package com.example.myphotos.ui.photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myphotos.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
    }

    private lateinit var viewModel: PhotosModel
    private lateinit var recyclerView: RecyclerView
    private var adapter = PhotosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_photos, container, false)

        viewModel = ViewModelProvider(this)[PhotosModel::class.java]
        recyclerView = view.findViewById(R.id.photos_list)

        viewModel.pagePhotos.observe(viewLifecycleOwner, Observer { newPagePhotos ->
            lifecycleScope.launch {
                adapter.submitData(newPagePhotos)
            }
        })

        recyclerView.adapter = adapter

        return view
    }
}