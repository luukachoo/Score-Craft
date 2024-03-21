package com.feature.series.screen.adapter

import com.core.common.base.BaseRecyclerAdapter
import com.core.common.extension.convertDate
import com.feature.series.databinding.ItemSeriesBinding
import com.feature.series.model.Series

class SeriesRecyclerAdapter :
    BaseRecyclerAdapter<Series, ItemSeriesBinding>(ItemSeriesBinding::inflate) {
    private var onClick: ((Series) -> Unit)? = null

    override fun onBind(binding: ItemSeriesBinding, position: Int) {
        val model = getItem(position)
        binding.apply {
            tvName.text = model.fullName
            tvBeginDate.text = model.beginAt.convertDate()
            tvEndDate.text = model.endAt?.convertDate()
            tvSeason.text = model.season
            root.setOnClickListener { onClick?.invoke(model) }
        }
    }

    fun onClick(click: (Series) -> Unit) {
        this.onClick = click
    }
}