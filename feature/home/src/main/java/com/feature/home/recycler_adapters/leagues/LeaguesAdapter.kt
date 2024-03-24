package com.feature.home.recycler_adapters.leagues

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.loadImagesWithGlide
import com.feature.home.databinding.ItemLeagueBinding
import com.feature.home.model.League

class LeaguesAdapter :
    BaseRecyclerAdapter<League, ItemLeagueBinding>(ItemLeagueBinding::inflate) {

    private var onLeagueClick: ((League) -> Unit)? = null
    private var onFavouriteClick: ((League) -> Unit)? = null

    override fun onBind(binding: ItemLeagueBinding, position: Int) {
        val league = getItem(position)
        binding.apply {
            tvName.text = league.name
            ivImage.loadImagesWithGlide(league.imageUrl)
            favouriteBtn.setOnClickListener { onFavouriteClick?.invoke(league) }

            root.setOnClickListener { onLeagueClick?.invoke(league) }
        }
    }

    fun onLeagueClick(click: (League) -> Unit) {
        this.onLeagueClick = click
    }

    fun onFavouriteClick(click: (League) -> Unit) {
        this.onFavouriteClick = click
    }
}