package com.example.new_tourslate.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.new_tourslate.data.retrofit.ApiConfig
import com.example.new_tourslate.data.retrofit.ApiService
import com.example.new_tourslate.data.retrofit.TranslateResponse
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
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        apiService = ApiConfig.getApiService()

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
        binding.settingButton.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        //button click
        binding.translateButton.setOnClickListener {
            val textToTranslate = binding.editText.text.toString()
            if (textToTranslate.isNotEmpty()) {
                uploadText(textToTranslate)
            }
        }
    }
    private fun uploadText(text: String) {
//        val requestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), text)
        val call = apiService.uploadText(text)

        call.enqueue(object : Callback<TranslateResponse> {
            override fun onResponse(call: Call<TranslateResponse>, response: Response<TranslateResponse>) {
                if (response.isSuccessful) {
                    val translateResponse = response.body()
                    binding.result.text = translateResponse?.data?.translatedText ?: "Translation failed"
                } else {
                    binding.result.text = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<TranslateResponse>, t: Throwable) {
                binding.result.text = "Error: ${t.message}"
            }
        })
    }
}
