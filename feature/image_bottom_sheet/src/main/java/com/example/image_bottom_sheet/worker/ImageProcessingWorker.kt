package com.example.image_bottom_sheet.worker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ImageProcessingWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val imageUriString = inputData.getString("imageUri") ?: return Result.failure()
        val imageUri = Uri.parse(imageUriString)

        val compressedUri = compressImage(imageUri, applicationContext)
        return if (compressedUri != null) {
            val outputData = Data.Builder()
                .putString("compressedImageUri", compressedUri.toString())
                .build()
            Result.success(outputData)
        } else {
            Result.failure()
        }
    }

    private fun compressImage(imageUri: Uri, context: Context): Uri? {
        var inputStream: InputStream? = null
        try {
            inputStream = context.contentResolver.openInputStream(imageUri)
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            val rotatedBitmap = rotateImageIfRequired(context, originalBitmap, imageUri)
            val compressedFile = File.createTempFile("compressed_", ".jpg", context.cacheDir)

            FileOutputStream(compressedFile).use { outStream ->
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outStream)
            }

            return Uri.fromFile(compressedFile)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            inputStream?.close()
        }
    }

    private fun rotateImageIfRequired(context: Context, img: Bitmap, imageUri: Uri): Bitmap {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        val exifInterface = inputStream?.let { ExifInterface(it) }
        inputStream?.close()

        val orientation = exifInterface?.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        ) ?: ExifInterface.ORIENTATION_NORMAL

        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
        }

        return Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
    }
}
