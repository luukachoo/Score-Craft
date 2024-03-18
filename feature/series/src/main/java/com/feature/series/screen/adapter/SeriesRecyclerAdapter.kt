package com.feature.series.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.convertDate
import com.feature.series.databinding.ItemSeriesBinding
import com.feature.series.model.Series

class SeriesRecyclerAdapter :
    ListAdapter<Series, SeriesRecyclerAdapter.SeriesViewHolder>(SeriesDiffCallBack()) {

    private var onClick: ((Series) -> Unit)? = null

    inner class SeriesViewHolder(private val binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: Series

        fun bind() = binding.apply {
            model = currentList[adapterPosition]

            tvName.text = model.fullName
            tvBeginDate.text = model.beginAt.convertDate()
            tvEndDate.text = model.endAt?.convertDate()
            tvSeason.text = model.season

            root.setOnClickListener { onClick?.invoke(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SeriesViewHolder(
        ItemSeriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind()
    }

    fun onClick(click: (Series) -> Unit) {
        this.onClick = click
    }
}