package com.example.myapplication.presentation.photo.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemPhotoBinding
import com.example.myapplication.domain.model.PhotoItem

class PhotoListAdapter(
    private val onItemClick: (PhotoItem) -> Unit
) : ListAdapter<PhotoItem, PhotoListAdapter.PhotoViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhotoViewHolder(
        private val binding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: PhotoItem) {
            binding.tvAuthor.text = photo.author
            binding.tvPhotoId.text = "ID: ${photo.id}"

            // Load thumbnail image with Coil
            binding.ivThumbnail.load(photo.downloadUrl) {
                crossfade(true)
                transformations(RoundedCornersTransformation(8f))
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }

            binding.root.setOnClickListener {
                onItemClick(photo)
            }
        }
    }

    private class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem == newItem
        }
    }
}

