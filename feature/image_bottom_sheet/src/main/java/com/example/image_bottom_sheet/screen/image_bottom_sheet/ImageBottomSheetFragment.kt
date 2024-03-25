package com.example.image_bottom_sheet.screen.image_bottom_sheet

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.core.common.base.BaseBottomSheetFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.core.common.extension.showSnackbar
import com.example.image_bottom_sheet.databinding.FragmentImageBottomSheetBinding
import com.example.image_bottom_sheet.worker.ImageProcessingWorker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import com.core.common.R

@AndroidEntryPoint
class ImageBottomSheetFragment :
    BaseBottomSheetFragment<FragmentImageBottomSheetBinding>(FragmentImageBottomSheetBinding::inflate) {

    private lateinit var photoFile: File

    private val takePictureResultLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                processImage(photoFile.toUri())
            } else {
                binding.root.showSnackbar(getString(R.string.camera_selection_cancelled))
            }
        }

    private val pickImageResultLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { processImage(it) }
        }

    override fun bind() {
        binding.apply {
            btnTakePicture.setOnClickListener {
                openCamera()
            }

            btnChooseGallery.setOnClickListener {
                openGallery()
            }
        }
    }

    private fun openCamera() {
        photoFile = createImageFile()
        val photoURI = FileProvider.getUriForFile(
            requireContext(),
            getString(
                R.string.provider,
                requireContext().packageName
            ),
            photoFile
        )
        takePictureResultLauncher.launch(photoURI)
    }

    private fun openGallery() {
        pickImageResultLauncher.launch(getString(R.string.image))
    }

    private fun createImageFile(): File {
        val storageDir: File? = requireContext().getExternalFilesDir(null)
        return File.createTempFile(
            "JPEG_${System.currentTimeMillis()}_",
            ".jpg",
            storageDir
        ).also { photoFile = it }
    }

    private fun processImage(imageUri: Uri) {
        val workData = Data.Builder()
            .putString(getString(R.string.imageuri), imageUri.toString())
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ImageProcessingWorker>()
            .setInputData(workData)
            .build()

        WorkManager.getInstance(requireContext()).enqueue(workRequest).apply {
            WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(workRequest.id)
                .observe(viewLifecycleOwner) { workInfo ->
                    if (workInfo != null && workInfo.state.isFinished) {
                        val compressedImageUriString = workInfo.outputData.getString(getString(R.string.compressedimageuri))
                        compressedImageUriString?.let {
                            navigateWithUri(it.toUri())
                        }
                    }
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        photoFile.let {
            outState.putString(getString(R.string.photofilepath), it.absolutePath)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getString(getString(R.string.photofilepath))?.let {
            photoFile = File(it)
        }
    }

    private fun navigateWithUri(uri: Uri) {
        findNavController().deepLinkNavigateTo(
            DeepLinkDestination.ProfileWithImageUri(uri), true
        )
    }
}
