package com.example.new_tourslate.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.new_tourslate.R
import com.example.new_tourslate.databinding.ActivitySignupBinding
import com.example.new_tourslate.ui.login.LoginActivity
import com.example.new_tourslate.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            btnSignup.setOnClickListener {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                if (email.isEmpty()){
                    etEmail.error = "Email harus diisi"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.error = "Email tidak valid"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (password.isEmpty() || password.length < 8){
                    etPassword.error = "Password harus lebih dari 8 karakter"
                    etPassword.requestFocus()
                    return@setOnClickListener
                }

                registerUser(email, password)
            }

            btnLoginHere.setOnClickListener {
                Intent(this@SignupActivity, LoginActivity::class.java).also{
                    startActivity(it)
                }
            }
        }

    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this@SignupActivity, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@SignupActivity, MainActivity::class.java).also { homeIntent ->
                homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(homeIntent)
            }
        }
    }
}