package com.example.friend_profile.screen

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.core.common.extension.loadImagesWithGlide
import com.example.friend_profile.adapter.FriendProfileRecyclerAdapter
import com.example.friend_profile.databinding.FragmentFriendProfileFragmentBinding
import com.example.friend_profile.event.FriendProfileEvent
import com.example.friend_profile.extension.showSnackBar
import com.example.friend_profile.state.FriendProfileState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendProfileFragment :
    BaseFragment<FragmentFriendProfileFragmentBinding>(FragmentFriendProfileFragmentBinding::inflate) {

    private val viewModel: FriendProfileViewModel by viewModels()
    private lateinit var adapter: FriendProfileRecyclerAdapter
    private val friendId by lazy { arguments?.getString("friendId") ?: "" }

    override fun bind() {
        viewModel.onEvent(FriendProfileEvent.FetchFriendData(friendId))
        viewModel.onEvent(FriendProfileEvent.FetchFriendFavouriteLeagues(friendId))

        adapter = FriendProfileRecyclerAdapter()
        binding.rvLeague.adapter = adapter
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.friendProfileState.collect {
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
            backBtn.setOnClickListener {
                viewModel.onEvent(FriendProfileEvent.OnBackButtonClick)
            }

            tvRemoveChatForUser.setOnClickListener {
                viewModel.onEvent(FriendProfileEvent.RemoveChatForCurrentUser(friendId))
            }

            tvRemoveChatForEveryone.setOnClickListener {
                viewModel.onEvent(FriendProfileEvent.RemoveChatForBothUsers(friendId))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleFriendRequestState(state: FriendProfileState) {
        binding.apply {
            progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

            state.friend?.let {
                ivAvatar.loadImagesWithGlide(it.avatar)
                tvFullName.text = "${it.firstName} ${it.lastName}"
                tvUserName.text = it.userName
            }

            state.leagues?.let {
                adapter.submitList(it)
            }

            state.errorMessage?.let { message ->
                root.showSnackBar(message = message)
                viewModel.onEvent(FriendProfileEvent.ResetErrorMessage)
            }
        }
    }

    private fun handleNavigationEvents(event: FriendProfileViewModel.FriendProfileUiEvent) {
        when (event) {
            FriendProfileViewModel.FriendProfileUiEvent.NavigateToMessage -> {
                findNavController().deepLinkNavigateTo(DeepLinkDestination.Message(friendId))
            }
        }
    }
}
