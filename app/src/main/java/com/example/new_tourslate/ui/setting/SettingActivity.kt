package com.example.new_tourslate.ui.setting

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.new_tourslate.R
import com.example.new_tourslate.data.pref.SettingPreferences
import com.example.new_tourslate.data.pref.dataStore
import com.example.new_tourslate.databinding.ActivitySettingBinding
import com.example.new_tourslate.ui.login.LoginActivity
import com.example.new_tourslate.ui.main.MainActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.closeSetting.setOnClickListener {
            onBackPressed()
        }

        //Night mode code
        val switchTheme = findViewById<SwitchMaterial>(R.id.switchNightMode)

        val pref = SettingPreferences.getInstance(application.dataStore)

        val SwitchViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        SwitchViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            SwitchViewModel.saveThemeSetting(isChecked)
        }

        // Logout button click listener
        binding.logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this@SettingActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}