package com.feature.home.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.ItemHeaderSectionBinding
import com.feature.home.databinding.ItemLeagueBinding
import com.feature.home.databinding.ItemLeaguesSectionBinding
import com.feature.home.model.League
import com.feature.home.model.Product
import com.feature.home.recycler_adapters.category.LeaguesAdapter

class MainRecyclerAdapter(
    private val categories: List<League>,
//    private val products: List<Product>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onProductClick: ((Product) -> Unit)? = null

    inner class HeaderViewHolder(binding: ItemHeaderSectionBinding) :
        RecyclerView.ViewHolder(binding.root)
    inner class CategoriesViewHolder(private val binding: ItemLeaguesSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCategories(categories: List<League>) = with(binding) {
            val leagueAdapter = LeaguesAdapter()
            leagueAdapter.submitList(categories)
            rvLeagues.adapter = leagueAdapter
        }
    }

//    inner class ProductsViewHolder(private val binding: ItemProductsSectionBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bindProducts(products: List<Product>) = with(binding) {
//            val productAdapter = ProductsAdapter()
//            productAdapter.submitList(products)
//            rvProducts.adapter = productAdapter
//        }
//    }

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
//            else -> {
////                val productsBinding = ItemProductsSectionBinding.inflate(
////                    LayoutInflater.from(parent.context),
////                    parent,
////                    false
////                )
////                ProductsViewHolder(productsBinding)
//            }
            else -> {
                val leagueBinding = ItemLeaguesSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CategoriesViewHolder(leagueBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> Unit
            is CategoriesViewHolder -> holder.bindCategories(categories)
//            is ProductsViewHolder -> holder.bindProducts()
        }
    }

    override fun getItemCount(): Int = 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            else -> LEAGUES
        }
    }

    fun onPostClick(click: (Product) -> Unit) {
        this.onProductClick = click
    }

    companion object {
        private const val HEADER = 0
        private const val LEAGUES = 1
//        private const val PRODUCTS = 2
    }
}