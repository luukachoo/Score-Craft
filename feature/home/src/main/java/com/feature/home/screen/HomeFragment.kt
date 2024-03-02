package com.feature.home.screen

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.base.BaseFragment
import com.feature.home.databinding.FragmentHomeBinding
import com.feature.home.event.HomeFragmentEvent
import com.feature.home.event.HomeNavigationEvents
import com.core.common.extension.showSnackbar
import com.feature.home.recycler_adapters.MainRecyclerAdapter
import com.feature.home.state.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter
    override fun bind() {
        mainRecyclerAdapter = MainRecyclerAdapter(emptyList())
        viewModel.onEvent(HomeFragmentEvent.FetchCategories)
        viewModel.onEvent(HomeFragmentEvent.FetchProducts)
    }

    override fun bindViewActionListeners() {
        mainRecyclerAdapter.onPostClick { product ->
            viewModel.onEvent(HomeFragmentEvent.ItemClick(product.id))
        }

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleHomeState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeUiEvent.collect {
                    handleNavigationEvents(it)
                }
            }
        }
    }

    private fun handleHomeState(state: HomeState) = with(binding) {
        progressBar.isVisible = state.isLoading

        mainRecyclerAdapter = MainRecyclerAdapter(state.categories ?: emptyList())
        mainRecyclerView.adapter = mainRecyclerAdapter

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(HomeFragmentEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: HomeNavigationEvents) {

    }

}