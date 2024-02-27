package com.feature.home.recycler_adapters.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.ItemProductPhotoBinding
import com.feature.home.extension.loadImagesWithGlide

class ProductImageAdapter(private val photos: List<String>) :
    RecyclerView.Adapter<ProductImageAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(private val binding: ItemProductPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) = with(binding) {
            ivProductImage.loadImagesWithGlide(url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        ItemProductPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }
}