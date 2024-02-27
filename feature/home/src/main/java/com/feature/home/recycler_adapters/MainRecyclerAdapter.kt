package com.feature.home.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.ItemCategoriesSectionBinding
import com.feature.home.databinding.ItemHeaderSectionBinding
import com.feature.home.databinding.ItemProductsSectionBinding
import com.feature.home.model.Category
import com.feature.home.model.Product
import com.feature.home.recycler_adapters.category.CategoriesAdapter
import com.feature.home.recycler_adapters.product.ProductsAdapter

class MainRecyclerAdapter(
    private val categories: List<Category>,
    private val products: List<Product>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onProductClick: ((Product) -> Unit)? = null

    inner class HeaderViewHolder(binding: ItemHeaderSectionBinding) :
        RecyclerView.ViewHolder(binding.root)
    inner class CategoriesViewHolder(private val binding: ItemCategoriesSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCategories(categories: List<Category>) = with(binding) {
            val categoryAdapter = CategoriesAdapter()
            categoryAdapter.submitList(categories)
            rvCategories.adapter = categoryAdapter
        }
    }

    inner class ProductsViewHolder(private val binding: ItemProductsSectionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindProducts(products: List<Product>) = with(binding) {
            val productAdapter = ProductsAdapter()
            productAdapter.submitList(products)
            rvProducts.adapter = productAdapter
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
            CATEGORIES -> {
                val categoriesBinding = ItemCategoriesSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CategoriesViewHolder(categoriesBinding)
            }
            else -> {
                val productsBinding = ItemProductsSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ProductsViewHolder(productsBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> Unit
            is CategoriesViewHolder -> holder.bindCategories(categories)
            is ProductsViewHolder -> holder.bindProducts(products)
        }
    }

    override fun getItemCount(): Int = 3

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            1 -> CATEGORIES
            else -> PRODUCTS
        }
    }

    fun onPostClick(click: (Product) -> Unit) {
        this.onProductClick = click
    }

    companion object {
        private const val HEADER = 0
        private const val CATEGORIES = 1
        private const val PRODUCTS = 2
    }
}