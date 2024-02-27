package com.feature.home.recycler_adapters.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.ItemProductBinding
import com.feature.home.model.Product

class ProductsAdapter :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductsDiffCallback()) {
    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            tvPrice.text = product.price.toString()
            tvTitle.text = product.title
            rvItemPhotos.adapter = ProductImageAdapter(product.images)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}