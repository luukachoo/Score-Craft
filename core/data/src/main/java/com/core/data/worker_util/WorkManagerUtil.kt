package com.core.data.worker_util

import android.content.Context
import android.net.Uri
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.core.data.worker.UploadImageWorker

object WorkManagerUtil {

    fun enqueueUploadWork(context: Context, userId: String, imageUri: Uri) {
        val inputData = Data.Builder().apply {
            putString("userId", userId)
            putString("imageUri", imageUri.toString())
        }.build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadImageWorker::class.java)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context).enqueue(uploadRequest)
    }
}
