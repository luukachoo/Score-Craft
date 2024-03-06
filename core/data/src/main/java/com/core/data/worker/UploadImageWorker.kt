package com.core.data.worker

import android.content.Context
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UploadImageWorker(
    @ApplicationContext context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val userId = inputData.getString("userId") ?: return@withContext Result.failure()
        val imageUriString = inputData.getString("imageUri") ?: return@withContext Result.failure()
        val imageUri = Uri.parse(imageUriString)

        try {
            val storageReference = FirebaseStorage.getInstance().reference.child("user_profiles/$userId/profile_picture.jpg")

            // Initiate upload and wait for it to complete
            val uploadTask = storageReference.putFile(imageUri).await()
            val downloadUrl = storageReference.downloadUrl.await()

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}