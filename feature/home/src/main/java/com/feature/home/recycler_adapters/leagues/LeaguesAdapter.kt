package com.feature.home.recycler_adapters.leagues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.feature.home.databinding.ItemLeagueBinding
import com.feature.home.model.League

class LeaguesAdapter :
    ListAdapter<League, LeaguesAdapter.LeagueViewHolder>(LeaguesDiffCallback()) {

    private var onLeagueClick: ((League) -> Unit)? = null
    private var onFavouriteClick: ((League) -> Unit)? = null

    inner class LeagueViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(league: League) = binding.apply {
            tvName.text = league.name
            ivImage.loadImagesWithGlide(league.imageUrl)
            favouriteBtn.setOnClickListener { onFavouriteClick?.invoke(league) }

            root.setOnClickListener { onLeagueClick?.invoke(league) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LeagueViewHolder(
        ItemLeagueBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun onLeagueClick(click: (League) -> Unit) {
        this.onLeagueClick = click
    }

    fun onFavouriteClick(click: (League) -> Unit) {
        this.onFavouriteClick = click
    }
}