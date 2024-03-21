package com.feature.series.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.feature.series.databinding.FragmentSeriesBinding
import com.feature.series.event.SeriesEvent
import com.feature.series.extension.showSnackBar
import com.feature.series.adapter.SeriesRecyclerAdapter
import com.feature.series.state.SeriesState
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
        adapter.onClick {
            viewModel.onEvent(SeriesEvent.NavigateToTournaments(it.slug))
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
            SeriesViewModel.SeriesUiEvent.NavigateToHome -> findNavController().popBackStack()
            is SeriesViewModel.SeriesUiEvent.NavigateToTournament -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Tournament(event.slug)
            )
        }
    }
}