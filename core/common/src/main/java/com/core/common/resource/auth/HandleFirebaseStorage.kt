package com.core.common.resource.auth

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HandleFirebaseStorage @Inject constructor() {
    private val storageReference = FirebaseStorage.getInstance().reference

    suspend fun uploadImage(userId: String, imageUri: Uri): String? {
        val filePath = storageReference.child("user_profiles/$userId/profile_picture.jpg")
        return try {
            filePath.putFile(imageUri).await()
            filePath.downloadUrl.await().toString()
        } catch (e: Exception) {
            null
        }
    }
}