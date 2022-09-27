package com.example.myphotos.ui.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myphotos.R
import com.example.myphotos.addImageIntoView
import com.example.myphotos.entity.MyPhoto

class PhotosAdapter : PagingDataAdapter<MyPhoto, PhotoItemViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.photos_item_view, parent, false)
        return PhotoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.authorName.text = item.author
            addImageIntoView(holder.photoImage, item.downloadUrl)
        }
    }

    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyPhoto>() {
            override fun areItemsTheSame(oldItem: MyPhoto, newItem: MyPhoto): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MyPhoto, newItem: MyPhoto): Boolean =
                oldItem == newItem
        }
    }
}

class PhotoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val authorName: TextView = itemView.findViewById(R.id.author_name)
    val photoImage: ImageView = itemView.findViewById(R.id.photo_image)
}
