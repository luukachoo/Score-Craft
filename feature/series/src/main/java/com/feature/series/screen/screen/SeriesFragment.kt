package com.feature.series.screen.screen

import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.core.common.base.BaseFragment
import com.feature.series.R
import com.feature.series.databinding.FragmentSeriesBinding
import com.feature.series.screen.adapter.SeriesRecyclerAdapter
import com.feature.series.screen.event.SeriesEvent
import com.feature.series.screen.extension.showSnackBar
import com.feature.series.screen.state.SeriesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeriesFragment : BaseFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate) {

    private val viewModel: SeriesViewModel by viewModels()
    private lateinit var adapter: SeriesRecyclerAdapter

    override fun bind() {
        binding.apply {
            adapter = SeriesRecyclerAdapter()
            rvSeries.adapter = adapter
        }

        val slugArg = arguments?.getString("slug") ?: ""
        viewModel.onEvent(SeriesEvent.FetchSeriesBySlug(slugArg))
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.seriesState.collect {
                    handleSeriesState(seriesState = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }
    }

    override fun bindViewActionListeners() {
        binding.apply {
            backBtn.setOnClickListener {
                viewModel.onEvent(SeriesEvent.NavigateToHome)
            }
        }
    }

    private fun handleSeriesState(seriesState: SeriesState) {
        binding.apply {
            progress.visibility =
                if (seriesState.isLoading) View.VISIBLE else View.GONE

            seriesState.series?.let {
                adapter.submitList(it)
            }

            seriesState.errorMessage?.let {
                root.showSnackBar(message = it)
                viewModel.onEvent(SeriesEvent.ResetErrorMessage)
            }
        }
    }

    private fun handleNavigationEvents(event: SeriesViewModel.SeriesUiEvent) {
        when (event) {
            SeriesViewModel.SeriesUiEvent.NavigateToHome -> handleNavigation("market-mingle://feature.home/fragment_home")

            else -> {}
        }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.seriesFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }
}