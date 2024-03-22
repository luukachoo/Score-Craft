package com.example.profile.screen.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log.d
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.core.common.R
import com.core.common.base.BaseFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.core.common.extension.loadImagesWithGlide
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.event.profile.ProfileEvent
import com.example.profile.extension.loadImageWithUri
import com.example.profile.extension.showSnackBar
import com.example.profile.state.profile.ProfileState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileFragmentViewModel by viewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    override fun bind() {
        d("ThemeDebug", "Binding fragment bottom sheet")
        viewModel.onEvent(ProfileEvent.GetCurrentUser)
        initializePermissionRequest()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profileState.collect {
                    handleRegisterState(state = it)
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
            ivAvatar.setOnClickListener {
                checkAndRequestPermissions()
            }

            buttonSettings.setOnClickListener {
                showSettingsBottomSheet()
            }
        }
    }

    private fun handleNavigationEvents(event: ProfileFragmentViewModel.ProfileUiEvent) {
        when (event) {
            ProfileFragmentViewModel.ProfileUiEvent.NavigateToHome -> findNavController().deepLinkNavigateTo(
                DeepLinkDestination.Home,
                popUpTo = true
            )

            ProfileFragmentViewModel.ProfileUiEvent.NavigateToWelcome -> {
                findNavController().deepLinkNavigateTo(
                    DeepLinkDestination.Welcome,
                    popUpTo = true
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleRegisterState(state: ProfileState) = binding.apply {
        progress.visibility = if (state.isLoading) View.VISIBLE else View.GONE

        state.user?.let { user ->
            tvFullName.text = "${user.firstName} ${user.lastName}"
            tvUserName.text = "@${user.userName}"

            val imageUriString = arguments?.getString("imageUri")

            if (!imageUriString.isNullOrEmpty()) {
                if (!state.imageUploaded) {
                    ivAvatar.loadImageWithUri(imageUriString.toUri())
                    user.userId.let { userId ->
                        viewModel.onEvent(
                            ProfileEvent.UploadProfileImage(
                                userId,
                                imageUriString.toUri()
                            )
                        )
                    }
                }
            } else if (user.avatar.isNotEmpty()) {
                ivAvatar.loadImagesWithGlide(user.avatar)
            }
        }

        state.errorMessage?.let { message ->
            root.showSnackBar(message)
            viewModel.onEvent(ProfileEvent.ResetErrorMessage)
        }
    }

    private fun checkAndRequestPermissions() {
        val requiredPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.CAMERA)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
        }
        val missingPermissions = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (missingPermissions.isNotEmpty()) {
            val shouldShowRationale = missingPermissions.any { permission ->
                shouldShowRequestPermissionRationale(permission)
            }

            if (shouldShowRationale) {
                showRationaleDialog()
            } else {
                requestPermissionLauncher.launch(missingPermissions)
            }
        } else {
            showImageBottomSheet()
        }
    }

    private fun showRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.feature_profile_permission_title))
            .setMessage(getString(R.string.feature_profile_message))
            .setPositiveButton(getString(R.string.feature_profile_dialog_positive_button)) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton(R.string.feature_profile_dialog_negative_button, null)
            .show()
    }

    private fun showImageBottomSheet() =
        findNavController().deepLinkNavigateTo(DeepLinkDestination.BottomSheet)

    private fun showSettingsBottomSheet() =
        findNavController().deepLinkNavigateTo(DeepLinkDestination.ShowSettingsBottomSheet)

    private fun initializePermissionRequest() {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val allPermissionsGranted = permissions.entries.all { it.value }
                if (allPermissionsGranted) {
                    showImageBottomSheet()
                } else {
                    binding.root.showSnackBar(getString(R.string.feature_profile_permission_required_message))
                }
            }
    }
}