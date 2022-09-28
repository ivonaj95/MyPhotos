package com.example.myphotos.ui.shimmer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myphotos.R

class FooterAdapter() : LoadStateAdapter<FooterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shimmer_view, parent, false)
        return FooterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
    }
}

class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}