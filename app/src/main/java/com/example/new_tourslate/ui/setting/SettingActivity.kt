package com.example.new_tourslate.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.new_tourslate.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeSetting.setOnClickListener {
            // Handle back button click here
            onBackPressed()
        }
    }
}