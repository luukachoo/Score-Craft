package com.example.marketmingle.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.marketmingle.R
import com.example.marketmingle.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    private var request = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        handleBottomNavVisibility()
    }


    private fun handleBottomNavVisibility() = with(binding) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.example.splash_screen.R.id.splashScreenFragment -> bottomNavigationView.isVisible =
                    false

                com.example.forgot_password.R.id.forgotPasswordFragment -> bottomNavigationView.isVisible =
                    false

                com.example.login.R.id.loginFragment -> bottomNavigationView.isVisible = false
                com.example.registration.R.id.registerFragment -> bottomNavigationView.isVisible =
                    false

                com.feature.welcome.R.id.welcomeFragment -> bottomNavigationView.isVisible = false
                else -> bottomNavigationView.isVisible = true
            }
        }
        requestPermission()
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            request.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
        }
    }
}