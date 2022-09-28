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
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myphotos.R
import com.example.myphotos.ui.shimmer.FooterAdapter
import com.facebook.shimmer.ShimmerFrameLayout
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
    private lateinit var shimmerLayout: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_photos, container, false)

        viewModel = ViewModelProvider(this)[PhotosModel::class.java]
        recyclerView = view.findViewById(R.id.photos_list)
        shimmerLayout = view.findViewById(R.id.shimmerLayout)
        val adapterWithLoading = adapter.withLoadStateFooter(FooterAdapter())
        adapter.addLoadStateListener { states ->
            when (states.source.refresh) {
                is LoadState.Loading -> {
                    shimmerLayout.startShimmer()
                    shimmerLayout.visibility = View.VISIBLE
                    Log.d("IVONA", "loading....")
                }
                is LoadState.NotLoading -> {
                    shimmerLayout.stopShimmer()
                    shimmerLayout.visibility = View.GONE
                    Log.d("IVONA", "notLoading....")

                }
                is LoadState.Error -> {
                    Log.d("IVONA", "error....")

                }
            }
        }
        viewModel.pagePhotos.observe(viewLifecycleOwner, Observer { newPagePhotos ->
            lifecycleScope.launch {

                adapter.submitData(newPagePhotos)
            }
        })

        recyclerView.adapter = adapterWithLoading

        return view
    }
}