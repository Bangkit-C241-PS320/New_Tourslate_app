package com.example.new_tourslate.ui.splash


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.new_tourslate.R
import com.example.new_tourslate.databinding.ActivitySplashScreenBinding
import com.example.new_tourslate.ui.login.LoginActivity
import com.example.new_tourslate.ui.main.MainActivity
import com.example.new_tourslate.ui.setting.SettingPreferences
import com.example.new_tourslate.ui.setting.SettingViewModel
import com.example.new_tourslate.ui.setting.ViewModelFactory
import com.example.new_tourslate.ui.setting.dataStore


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val intent = Intent(this, LoginActivity::class.java)
//
//        // Set initial alpha to 0
//        binding.logo.alpha = 0f
//
//        // Animate alpha to 1
//        binding.logo.animate()
//            .setDuration(3000)
//            .alpha(1f)
//            .withEndAction {
//                startActivity(intent)
//                finish()
//            }
        val pref = SettingPreferences.getInstance(application.dataStore)
        val SwitchViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        SwitchViewModel.getThemeSettings().observe(
            this,
        ) {
            AppCompatDelegate.setDefaultNightMode(if (it) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}