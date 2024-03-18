package com.example.tournament.screen

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.core.common.extension.showSnackbar
import com.example.tournament.databinding.FragmentTournamentBinding
import com.example.tournament.event.TournamentEvent
import com.example.tournament.screen.adapter.TournamentRecyclerAdapter
import com.example.tournament.state.TournamentState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TournamentFragment :
    BaseFragment<FragmentTournamentBinding>(FragmentTournamentBinding::inflate) {

    private val adapter by lazy { TournamentRecyclerAdapter() }
    private val viewModel: TournamentFragmentViewModel by viewModels()

    override fun bind() {
        setUpRecycler()
        val slugArg = arguments?.getString("slug") ?: ""
        viewModel.onEvent(TournamentEvent.FetchTournaments(slugArg))
    }

    override fun bindViewActionListeners() {
        adapter.onClick {
            viewModel.onEvent(TournamentEvent.ItemClick(it.slug))
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    handleTournamentState(it)
                }
            }
        }
    }

    private fun handleTournamentState(state: TournamentState) = binding.apply {
        progressBar.isVisible = state.isLoading

        state.tournaments.let {
            adapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(TournamentEvent.ResetErrorMessage)
        }
    }

    private fun setUpRecycler() {
        binding.rvTournaments.adapter = adapter

    }
}