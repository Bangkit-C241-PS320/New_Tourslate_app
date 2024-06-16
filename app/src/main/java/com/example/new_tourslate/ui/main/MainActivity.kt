package com.example.new_tourslate.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.example.new_tourslate.R
import com.example.new_tourslate.data.retrofit.ApiConfig
import com.example.new_tourslate.databinding.ActivityMainBinding
import com.example.new_tourslate.ui.history.HistoryActivity
import com.example.new_tourslate.ui.login.LoginActivity
import com.example.new_tourslate.ui.setting.SettingActivity
//import okhttp3.MediaType.Companion.toMediaType
//import okhttp3.RequestBody.Companion.toRequestBody
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.HttpException
//import retrofit2.Response
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Check if the user is logged in
        if (auth.currentUser == null) {
            Intent(this, LoginActivity::class.java).also { loginIntent ->
                loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(loginIntent)
            }
            return
        }

        //go to history activity
        binding.historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        //go to setting activity
        binding.settingButton.setOnClickListener{
            startActivity(Intent(this, SettingActivity::class.java))
        }

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
//                val apiService = ApiConfig.getApiService()
//                val selectedItem = parent.getItemAtPosition(position).toString()
//                val requestBody = selectedItem.toRequestBody("text/plain".toMediaType())
//                apiService.uploadText(requestBody)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

//            override fun onNothingSelected(parent: AdapterView<*>) {
//                val apiService = ApiConfig.getApiService()
//                val requestBody = "Indonesia".toRequestBody("text/plain".toMediaType())
//                apiService.uploadText(requestBody)
//            }
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
                binding.spinnerInput2.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
//                            val selectedItem = parent.getItemAtPosition(position).toString()
//                            val requestBody = selectedItem.toRequestBody("text/plain".toMediaType())
//                            apiService.uploadText(requestBody)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

//                        override fun onNothingSelected(parent: AdapterView<*>) {
//                            val apiService = ApiConfig.getApiService()
//                            val requestBody = "English".toRequestBody("text/plain".toMediaType())
//                            apiService.uploadText(requestBody)
//                        }
                    }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

//            private fun getResult() {
//                val apiService = ApiConfig.getApiService()
//                apiService.getText().enqueue(object : Callback<CancerNewsResponse> {
//                    override fun onResponse(
//                        call: Call<CancerNewsResponse>,
//                        response: Response<CancerNewsResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data = response.body()
//                            // Assuming your data has a "title" field for display
//                            val title =
//                                data?.title ?: "No data available"  // Handle potential null value
//                            textView.text = title
//                        } else {
//                            // Handle API error
//                            textView.text = "Error fetching data"
//                        }
//                    }
//
//                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
//                        // Tampilkan pesan error jika request gagal
//                    }
//                })
//            }

//
        }
    }
}