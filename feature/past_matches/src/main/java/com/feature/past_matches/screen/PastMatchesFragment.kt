package com.feature.past_matches.screen

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.core.common.extension.showSnackbar
import com.feature.past_matches.databinding.FragmentPastMatchesBinding
import com.feature.past_matches.event.PastMatchesEvents
import com.feature.past_matches.state.PastMatchesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PastMatchesFragment :
    BaseFragment<FragmentPastMatchesBinding>(FragmentPastMatchesBinding::inflate) {

    private val viewModel: PastMatchesViewModel by viewModels()
    private val rvPastMatchesAdapter by lazy { PastMatchesRecyclerAdapter() }

    override fun bind() {
        setUpRecycler()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pastMatchesState.collect {
                    handlePastMatchesState(it)
                }
            }
        }
    }

    private fun handlePastMatchesState(state: PastMatchesState) = with(binding) {
        progressBar.isVisible = state.isLoading

        state.pastMatches.let {
            rvPastMatchesAdapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(message = it)
            viewModel.onEvent(PastMatchesEvents.ResetErrorMessage)
        }
    }

    private fun setUpRecycler() = with(binding) {
        rvPastMatches.adapter = rvPastMatchesAdapter
        viewModel.onEvent(PastMatchesEvents.FetchPastMatches)
    }
}