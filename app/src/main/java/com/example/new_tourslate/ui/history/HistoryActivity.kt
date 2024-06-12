package com.example.new_tourslate.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.new_tourslate.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            // Handle back button click here
            onBackPressed()
        }
    }
}