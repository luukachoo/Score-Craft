package com.example.marketmingle.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.marketmingle.R
import com.google.android.datatransport.runtime.logging.Logging
import com.google.android.datatransport.runtime.logging.Logging.d
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var request = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        setContentView(R.layout.activity_main)
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            request.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
        }
    }

    private fun jibia() {
        
    }
}