package com.feature.home.recycler_adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.feature.home.databinding.ItemHeaderSectionBinding
import com.feature.home.databinding.ItemLeaguesSectionBinding
import com.feature.home.model.League
import com.feature.home.model.auth.Users
import com.feature.home.recycler_adapters.leagues.LeaguesAdapter

class MainRecyclerAdapter(
    private val leagues: List<League>,
    private val model: Users?,
    private val imageUri: String,
    private val onLeagueItemClick: (League) -> Unit,
    private val onFavouriteClick: ((League) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onAvatarClick: (() -> Unit)? = null
    private var onNextPageClick: (() -> Unit)? = null
    private var onPrevPageClick: (() -> Unit)? = null

    inner class HeaderViewHolder(private val binding: ItemHeaderSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")

        fun bind(user: Users?, image: String) {
            binding.apply {
                ivAvatar.setOnClickListener {
                    onAvatarClick?.invoke()
                }

                val welcomeText = "Welcome ${user?.userName}"
                val spannableString = SpannableString(welcomeText)

                val userNameStart = welcomeText.indexOf("${user?.userName}")
                val userNameEnd = userNameStart + (user?.userName?.length ?: 0)

                val purpleColor = Color.parseColor("#A45EE5")

                spannableString.setSpan(
                    ForegroundColorSpan(purpleColor),
                    userNameStart,
                    userNameEnd,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                tvUserName.text = spannableString

                ivAvatar.loadImagesWithGlide(image)

                nextBtn.setOnClickListener {
                    onNextPageClick?.invoke()
                }

                prevBtn.setOnClickListener {
                    onPrevPageClick?.invoke()
                }
            }
        }
    }

    inner class LeaguesViewHolder(private val binding: ItemLeaguesSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCategories(leagues: List<League>) = binding.apply {
            val leagueAdapter = LeaguesAdapter().apply {
                onLeagueClick { league ->
                    onLeagueItemClick(league)
                }

                onFavouriteClick { league ->
                    onFavouriteClick(league)
                }
            }
            leagueAdapter.submitList(leagues)
            rvLeagues.adapter = leagueAdapter
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
            is HeaderViewHolder -> holder.bind(model, imageUri)
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

    fun onAvatarClick(click: () -> Unit) {
        this.onAvatarClick = click
    }

    fun onNextPageClick(click: () -> Unit) {
        this.onNextPageClick = click
    }

    fun onPrevPageClick(click: () -> Unit) {
        this.onPrevPageClick = click
    }

    companion object {
        private const val HEADER = 0
        private const val LEAGUES = 1
    }
}