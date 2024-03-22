package com.example.friend_request.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.example.friend_request.adapter.FriendRequestRecyclerAdapter
import com.example.friend_request.databinding.FragmentFriendRequestBinding
import com.example.friend_request.event.FriendRequestEvent
import com.example.friend_request.extension.showSnackBar
import com.example.friend_request.state.FriendRequestState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendRequestFragment :
    BaseFragment<FragmentFriendRequestBinding>(FragmentFriendRequestBinding::inflate) {

    private val viewModel: FriendRequestViewModel by viewModels()
    private lateinit var adapter: FriendRequestRecyclerAdapter

    override fun bind() {
        viewModel.onEvent(FriendRequestEvent.FetchFriends)
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.friendRequestState.collect {
                    handleFriendRequestState(state = it)
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
            adapter = FriendRequestRecyclerAdapter(
                onAccept = { user ->
                    viewModel.onEvent(FriendRequestEvent.AcceptFriendRequest(user.userId))
                },
                onReject = { user ->
                    viewModel.onEvent(FriendRequestEvent.RejectFriendRequest(user.userId))
                }
            )
            friendRequestRv.adapter = adapter

            backBtn.setOnClickListener {
                viewModel.onEvent(FriendRequestEvent.OnBackButtonClick)
            }
        }
    }

    private fun handleFriendRequestState(state: FriendRequestState) {
        binding.apply {
            progress.visibility =
                if (state.isLoading) View.VISIBLE else View.GONE

            state.friends?.let {
                adapter.submitList(it)
            }

            state.errorMessage?.let {
                root.showSnackBar(message = it)
                viewModel.onEvent(FriendRequestEvent.ResetErrorMessage)
            }

        }
    }

    private fun handleNavigationEvents(event: FriendRequestViewModel.FriendRequestUiEvent) {
        when (event) {
            FriendRequestViewModel.FriendRequestUiEvent.NavigateToChats -> findNavController().popBackStack()
        }
    }
}