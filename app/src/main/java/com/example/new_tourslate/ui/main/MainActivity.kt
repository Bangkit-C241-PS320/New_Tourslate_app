package com.example.new_tourslate.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.Spinner
import android.widget.Toast
import com.example.new_tourslate.R
import com.example.new_tourslate.data.retrofit.ApiConfig
import com.example.new_tourslate.databinding.ActivityMainBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_items_input,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerInput.adapter = adapter
        }

        // Set up a listener for the spinner
        binding.spinnerInput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val apiService = ApiConfig.getApiService()
                val selectedItem = parent.getItemAtPosition(position).toString()
                val requestBody = selectedItem.toRequestBody("text/plain".toMediaType())
                apiService.uploadText(requestBody)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                val apiService = ApiConfig.getApiService()
                val requestBody = "Indonesia".toRequestBody("text/plain".toMediaType())
                apiService.uploadText(requestBody)
            }
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_items_result,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerInput2.adapter = adapter
        }

        // Set up a listener for the spinner
        binding.spinnerInput2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val apiService = ApiConfig.getApiService()
        binding.spinnerInput2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                val requestBody = selectedItem.toRequestBody("text/plain".toMediaType())
                apiService.uploadText(requestBody)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                val apiService = ApiConfig.getApiService()
                val requestBody = "English".toRequestBody("text/plain".toMediaType())
                apiService.uploadText(requestBody)
            }
        }
    }
    private fun getResult(){
        val apiService = ApiConfig.getApiService()
        apiService.getText().enqueue(object  : Callback<CancerNewsResponse> {
            override fun onResponse(
                call: Call<CancerNewsResponse>,
                response: Response<CancerNewsResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    // Assuming your data has a "title" field for display
                    val title = data?.title ?: "No data available"  // Handle potential null value
                    textView.text = title
                } else {
                    // Handle API error
                    textView.text = "Error fetching data"
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // Tampilkan pesan error jika request gagal
            }
        })
    }

        val switchTheme = findViewById<SwitchMaterial>(R.id.switchNightMode)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingViewModel =
            ViewModelProvider(this, ViewModelFactory(pref)).get(SettingViewModel::class.java)
        settingViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            switchTheme.isChecked = isDarkModeActive
        }

    private fun postText(){
        val apiService = ApiConfig.getApiService()
        val text = binding.editText.text.toString()
        val requestBody = text.toRequestBody("text/plain".toMediaType())
        try {
            apiService.uploadText(requestBody)
        } catch (e: HttpException){
        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
            changeAppTheme(isChecked, this@MainActivity)
        }
    }

        }
    private fun changeAppTheme(isDarkModeActive: Boolean, context: Context) {
        if (isDarkModeActive) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}