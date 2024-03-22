package com.example.message.screen

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.loadImagesWithGlide
import com.example.message.R
import com.example.message.adapter.MessageRecyclerAdapter
import com.example.message.databinding.FragmentMessageBinding
import com.example.message.event.MessageEvent
import com.example.message.extension.showSnackBar
import com.example.message.model.Users
import com.example.message.state.MessageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MessageFragment : BaseFragment<FragmentMessageBinding>(FragmentMessageBinding::inflate) {

    private val viewModel: MessageFragmentViewModel by viewModels()
    private var adapter: MessageRecyclerAdapter? = null

    override fun bind() {
        val friendId = arguments?.getString("friendId") ?: ""
        viewModel.onEvent(MessageEvent.GetCurrentUser)
        viewModel.onEvent(MessageEvent.FetchFriend(friendId))
        viewModel.onEvent(MessageEvent.FetchMessages(friendId))
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.messageState.collect {
                    handleMessageState(it)
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
        val friendId = arguments?.getString("friendId") ?: ""

        binding.apply {
            btnSend.isEnabled = etTextField.text!!.isNotBlank()

            etTextField.doAfterTextChanged {
                val messageText = it.toString()
                btnSend.isEnabled = messageText.isNotBlank()
            }

            btnSend.setOnClickListener {
                val messageText = etTextField.text.toString()
                if (messageText.isNotBlank()) {
                    viewModel.onEvent(MessageEvent.SendMessage(friendId, messageText))
                    etTextField.text?.clear()
                }
            }

            backBtn.setOnClickListener {
                viewModel.onEvent(MessageEvent.OnBackButtonClick)
            }
        }
    }


    private fun handleMessageState(state: MessageState) {
        binding.apply {
            var user: Users? = null
            var friend: Users? = null

            state.user?.let {
                user = it
            }

            state.friend?.let {
                friend = it
                tvFullName.text = getString(R.string.full_name_format, it.firstName, it.lastName)
                tvUserName.text = getString(R.string.username_format, it.userName)
                ivAvatar.loadImagesWithGlide(it.avatar)
            }

            if (user != null && friend != null && adapter == null) {
                adapter = MessageRecyclerAdapter(user!!.userId, friend!!)
                rvChat.adapter = adapter
            }

            state.messages?.let { messages ->
                adapter?.submitList(messages) {
                    binding.rvChat.scrollToPosition(messages.size - 1)
                }
            }

            state.errorMessage?.let {
                if (it.isNotBlank()) {
                    root.showSnackBar(message = it)
                    viewModel.onEvent(MessageEvent.ResetErrorMessage)
                }
            }
        }
    }

    private fun handleNavigationEvents(event: MessageFragmentViewModel.MessageUiEvent) {
        when (event) {
            MessageFragmentViewModel.MessageUiEvent.NavigateToChats -> findNavController().popBackStack()
        }
    }
}