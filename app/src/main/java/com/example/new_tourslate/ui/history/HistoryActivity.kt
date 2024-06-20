package com.example.new_tourslate.ui.history

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.new_tourslate.data.adapter.HistoryListAdapter
import com.example.new_tourslate.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycle.layoutManager = LinearLayoutManager(this)

        getData()

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getData() {
        val adapter = HistoryListAdapter()
        binding.recycle.adapter = adapter
        historyViewModel.history.observe(this, { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        })
    }
}
