package com.feature.home.recycler_adapters.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.feature.home.databinding.ItemLeagueBinding
import com.feature.home.model.League

class LeaguesAdapter :
    ListAdapter<League, LeaguesAdapter.CategoryViewHolder>(CategoryDiffCallback()) {
    inner class CategoryViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(league: League) = with(binding) {
            tvName.text = league.name
            ivImage.loadImagesWithGlide(league.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemLeagueBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}