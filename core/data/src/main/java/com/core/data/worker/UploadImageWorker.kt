package com.core.data.worker

import android.content.Context
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class UploadImageWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val userId = inputData.getString("userId") ?: return Result.failure()
        val imageUriString = inputData.getString("imageUri") ?: return Result.failure()
        val imageUri = Uri.parse(imageUriString)

        return try {
            val storageReference =
                FirebaseStorage.getInstance().reference.child("user_profiles/$userId/profile_picture.jpg")
            storageReference.putFile(imageUri).await()
            val imageUrl = storageReference.downloadUrl.await().toString()

            val databaseReference = FirebaseDatabase.getInstance().getReference("Users/$userId")
            databaseReference.child("avatar").setValue(imageUrl).await()

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}