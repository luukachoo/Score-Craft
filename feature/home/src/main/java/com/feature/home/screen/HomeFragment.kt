package com.feature.home.screen

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.core.common.extension.showSnackbar
import com.feature.home.databinding.FragmentHomeBinding
import com.feature.home.event.HomeFragmentEvent
import com.feature.home.event.HomeNavigationEvents
import com.feature.home.recycler_adapters.MainRecyclerAdapter
import com.feature.home.state.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun bind() {
        mainRecyclerAdapter =
            MainRecyclerAdapter(
                emptyList(),
                null,
                "",
                onLeagueItemClick = { _ -> },
                onFavouriteClick = { _ -> })
        binding.mainRecyclerView.adapter = mainRecyclerAdapter

        viewModel.onEvent(HomeFragmentEvent.FetchCategories)
        viewModel.onEvent(HomeFragmentEvent.FetchProducts)
        viewModel.onEvent(HomeFragmentEvent.GetCurrentUser)
    }

    override fun bindViewActionListeners() {
        setupClickListeners()
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

        state.user?.let { user ->
            if (!state.imageFetched) {
                viewModel.onEvent(HomeFragmentEvent.FetchUserProfileImage(user.userId))
            }

            mainRecyclerAdapter = MainRecyclerAdapter(
                state.leagues ?: emptyList(),
                user,
                state.imageUri ?: "",
                onLeagueItemClick = { league ->
                    viewModel.onEvent(HomeFragmentEvent.SaveFavouriteLeague(league.slug))
                    findNavController().deepLinkNavigateTo(
                        DeepLinkDestination.Series(league.slug),
                        true
                    )
                },
                onFavouriteClick = {
                    viewModel.onEvent(HomeFragmentEvent.SaveFavouriteLeague(leagueSlug = it.slug))
                }
            )

            binding.mainRecyclerView.adapter = mainRecyclerAdapter

            setupClickListeners()
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(HomeFragmentEvent.ResetErrorMessage)
        }
    }

    private fun setupClickListeners() {
        mainRecyclerAdapter.onAvatarClick {
            findNavController().deepLinkNavigateTo(DeepLinkDestination.Profile, true)
        }

        mainRecyclerAdapter.onNextPageClick {
            viewModel.onEvent(HomeFragmentEvent.LoadNextPage)
        }

        mainRecyclerAdapter.onPrevPageClick {
            viewModel.onEvent(HomeFragmentEvent.LoadPreviousPage)
        }
    }

    private fun handleNavigationEvents(event: HomeNavigationEvents) {
        when (event) {
            HomeNavigationEvents.NavigateToProfile -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Profile,
                true
            )

            else -> {}
        }
    }
}