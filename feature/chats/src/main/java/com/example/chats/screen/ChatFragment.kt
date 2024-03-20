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
            adapter = ChatRecyclerAdapter { userId ->
                handleUserClick(userId)
            }
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
                val userName = etAddFriend.text.toString()

                viewModel.onEvent(ChatEvent.AddFriend(userName))
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

//            state.fcmToken?.let {
//                viewModel.onEvent(ChatEvent.SendFriendRequest(it))
//            }
        }
    }

    private fun handleUserClick(userId: String) {
        findNavController().deepLinkNavigateTo(DeepLinkDestination.Message(userId), true)
    }


    private fun handleNavigationEvents(event: ChatFragmentViewModel.ChatUiEvent) {
        when (event) {
            ChatFragmentViewModel.ChatUiEvent.NavigateToFriendRequest -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.FriendRequest,
                true
            )

            ChatFragmentViewModel.ChatUiEvent.NavigateToHome -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Home,
                true
            )

            is ChatFragmentViewModel.ChatUiEvent.NavigateToMessage -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Message(event.friendId),
                true
            )
        }
    }
}