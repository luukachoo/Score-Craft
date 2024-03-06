package com.core.common.resource.work_manager

import android.content.Context
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class UploadImageWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val imageUriString = inputData.getString("imageUri") ?: return Result.failure()
        val userId = inputData.getString("userId") ?: return Result.failure()
        val imageUri = Uri.parse(imageUriString)

        val storageRef =
            FirebaseStorage.getInstance().reference.child("user_profiles/$userId/profile_picture.jpg")

        return try {
            storageRef.putFile(imageUri).await()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}