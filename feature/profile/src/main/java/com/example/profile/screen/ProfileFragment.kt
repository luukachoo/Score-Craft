package com.example.profile.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.core.common.base.BaseFragment
import com.core.common.extension.loadImagesWithGlide
import com.example.profile.R
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.event.ProfileEvent
import com.example.profile.extension.loadImageWithUri
import com.example.profile.extension.showSnackBar
import com.example.profile.state.ProfileState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileFragmentViewModel by viewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    override fun bind() {
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

            backBtn.setOnClickListener {
                handleNavigation("market-mingle://feature.home/fragment_home")
            }

            logOutBtn.setOnClickListener {
                viewModel.onEvent(ProfileEvent.LogOut)
            }
        }
    }

    private fun handleNavigationEvents(event: ProfileFragmentViewModel.ProfileUiEvent) {
        when (event) {
            ProfileFragmentViewModel.ProfileUiEvent.NavigateToHome -> handleNavigation("market-mingle://feature.home/fragment_home")
            ProfileFragmentViewModel.ProfileUiEvent.NavigateToWelcome -> handleNavigation("market-mingle://feature.welcome/fragment_welcome")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleRegisterState(state: ProfileState) = binding.apply {
        progress.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.user?.let {
            tvFullName.text = "${it.firstName} ${it.lastName}"
            tvUserName.text = "@${it.userName}"
        }

        val imageUriString = arguments?.getString("imageUri") ?: ""

        if (imageUriString.isNotEmpty() && !state.imageUploaded) {
            val imageUri = imageUriString.toUri()
            binding.ivAvatar.loadImageWithUri(imageUri)

            state.user?.userId?.let { userId ->
                viewModel.onEvent(
                    ProfileEvent.UploadProfileImage(
                        userId = userId,
                        imageUri = imageUri
                    )
                )
            }
        }

        if (!state.imageFetched && !state.imageIsSet) {
            state.user?.userId?.let { userId ->
                viewModel.onEvent(ProfileEvent.FetchUserProfileImage(userId = userId))
            }
        }

        if (!state.imageIsSet) {
            state.imageUri?.let {
                binding.ivAvatar.loadImagesWithGlide(it)
            }
        }

        state.errorMessage?.let {
            root.showSnackBar(message = it)
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
            .setTitle("Permission Required")
            .setMessage("This feature requires camera and media access to function. Please enable permissions in app settings.")
            .setPositiveButton("App Settings") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showImageBottomSheet() {
        handleNavigation("market-mingle://feature.image_bottom_sheet/fragment_image_bottom_sheet")
    }

    private fun initializePermissionRequest() {
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val allPermissionsGranted = permissions.entries.all { it.value }
                if (allPermissionsGranted) {
                    showImageBottomSheet()
                } else {
                    binding.root.showSnackBar("Permissions required for this feature")
                }
            }
    }

    private fun handleNavigation(uri: String) {
        val parsedUri = uri.toUri()
        val request = NavDeepLinkRequest.Builder.fromUri(parsedUri).build()

        val navOptions = navOptions {
            popUpTo(R.id.profileFragment) { inclusive = true }
        }

        findNavController().navigate(request, navOptions)
    }
}