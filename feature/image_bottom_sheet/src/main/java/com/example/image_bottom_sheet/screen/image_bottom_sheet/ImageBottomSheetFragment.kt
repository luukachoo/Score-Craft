package com.example.image_bottom_sheet.screen.image_bottom_sheet

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseBottomSheetFragment
import com.core.common.extension.DeepLinkDestination
import com.core.common.extension.deepLinkNavigateTo
import com.example.image_bottom_sheet.databinding.FragmentImageBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@AndroidEntryPoint
class ImageBottomSheetFragment :
    BaseBottomSheetFragment<FragmentImageBottomSheetBinding>(FragmentImageBottomSheetBinding::inflate) {

    private lateinit var photoFile: File

    private val takePictureResultLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                val photoURI: Uri = FileProvider.getUriForFile(
                    requireContext(),
                    "${requireContext().packageName}.provider",
                    photoFile
                )
                val compressedUri = compressImage(photoURI, requireContext())
                if (compressedUri != null) {
                    findNavController().deepLinkNavigateTo(
                        DeepLinkDestination.ProfileWithImageUri(compressedUri), true
                    )
                }
            } else {
                // Handle the failure or cancellation
            }
        }

    private val pickImageResultLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                val compressedUri = compressImage(uri, requireContext())
                if (compressedUri != null) {
                    findNavController().deepLinkNavigateTo(
                        DeepLinkDestination.ProfileWithImageUri(compressedUri), true
                    )
                }
            } else {
                // Handle the failure or cancellation
            }
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
        val photoURI: Uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
        takePictureResultLauncher.launch(photoURI)
    }

    private fun openGallery() {
        pickImageResultLauncher.launch("image/*")
    }

    private fun createImageFile(): File {
        val storageDir: File? = requireContext().getExternalFilesDir(null)
        return File.createTempFile(
            "JPEG_${System.currentTimeMillis()}_",
            ".jpg",
            storageDir
        ).also { photoFile = it }
    }

    private fun compressImage(imageUri: Uri, context: Context): Uri? {
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
            val originalBitmap = BitmapFactory.decodeStream(inputStream)

            val compressedFile = createImageFile()
            FileOutputStream(compressedFile).use { outStream ->
                originalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outStream)
            }

            return FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                compressedFile
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}