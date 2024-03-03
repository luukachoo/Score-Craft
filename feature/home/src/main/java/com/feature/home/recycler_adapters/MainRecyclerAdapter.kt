package com.feature.home.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.ItemHeaderSectionBinding
import com.feature.home.databinding.ItemLeaguesSectionBinding
import com.feature.home.model.League
import com.feature.home.recycler_adapters.leagues.LeaguesAdapter

class MainRecyclerAdapter(
    private val leagues: List<League>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onLeagueClick: ((League) -> Unit)? = null

    inner class HeaderViewHolder(binding: ItemHeaderSectionBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class LeaguesViewHolder(private val binding: ItemLeaguesSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCategories(leagues: List<League>) = with(binding) {
            val leagueAdapter = LeaguesAdapter()
            leagueAdapter.submitList(leagues)
            rvLeagues.adapter = leagueAdapter

            leagueAdapter.onLeagueClick { league ->
                onLeagueClick?.invoke(league)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                val headerBinding = ItemHeaderSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(headerBinding)
            }

            else -> {
                val leagueBinding = ItemLeaguesSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LeaguesViewHolder(leagueBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> Unit
            is LeaguesViewHolder -> holder.bindCategories(leagues)
        }
    }

    override fun getItemCount(): Int = 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            else -> LEAGUES
        }
    }

    fun onLeagueClick(click: (League) -> Unit) {
        this.onLeagueClick = click
    }

    companion object {
        private const val HEADER = 0
        private const val LEAGUES = 1
    }
}