package com.example.new_tourslate.ui.history

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.new_tourslate.data.adapter.HistoryListAdapter
import com.example.new_tourslate.data.adapter.LoadingStateAdapter
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

        Log.d("HistoryActivity", "onCreate called")
        getData()

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getData() {
        val adapter = HistoryListAdapter()
        binding.recycle.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        Log.d("HistoryActivity", "Adapter set")
        historyViewModel.history.observe(this, { pagingData ->
            Log.d("HistoryActivity", "Data observed: $pagingData")
            adapter.submitData(lifecycle, pagingData)
        })
    }
}
