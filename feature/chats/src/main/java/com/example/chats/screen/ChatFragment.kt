package com.example.chats.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.example.chats.adapters.ChatRecyclerAdapter
import com.example.chats.databinding.FragmentChatBinding
import com.example.chats.event.ChatEvent
import com.example.chats.extension.showSnackBar
import com.example.chats.state.ChatState
import com.example.chats.util.CustomDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate) {

    private val viewModel: ChatFragmentViewModel by viewModels()
    private lateinit var adapter: ChatRecyclerAdapter

    override fun bind() {
        binding.apply {
            adapter = ChatRecyclerAdapter(
                onChatClicked = { userId ->
                    viewModel.onEvent(ChatEvent.OnFriendClick(userId))
                },
                onRemoveFriendClicked = { userId ->
                    viewModel.onEvent(ChatEvent.RemoveFriend(userId))
                }
            )
            chatRv.adapter = adapter
            chatRv.addItemDecoration(CustomDividerItemDecoration(requireContext()))
        }

        viewModel.onEvent(ChatEvent.FetchFriends)
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.chatState.collect {
                    handleChatState(state = it)
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
            ivAddFriend.setOnClickListener {
                viewModel.onEvent(ChatEvent.AddFriend(etAddFriend.text.toString().trim()))
            }

            ivFriendRequest.setOnClickListener {
                viewModel.onEvent(ChatEvent.OnRequestClick)
            }
        }
    }

    private fun handleChatState(state: ChatState) {
        binding.apply {
            progress.visibility =
                if (state.isLoading) View.VISIBLE else View.GONE

            state.friends?.let {
                adapter.submitList(it)
            }

            state.errorMessage?.let {
                root.showSnackBar(message = it)
                viewModel.onEvent(ChatEvent.ResetErrorMessage)
            }
        }
    }

    private fun handleNavigationEvents(event: ChatFragmentViewModel.ChatUiEvent) {
        when (event) {
            ChatFragmentViewModel.ChatUiEvent.NavigateToFriendRequest -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.FriendRequest,
            )

            is ChatFragmentViewModel.ChatUiEvent.NavigateToMessage -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Message(event.friendId),
            )
        }
    }
}