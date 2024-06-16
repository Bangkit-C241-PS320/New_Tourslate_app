package com.example.new_tourslate.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.new_tourslate.databinding.ActivityLoginBinding
import com.example.new_tourslate.ui.forgotpass.ForgotPasswordActivity
import com.example.new_tourslate.ui.main.MainActivity
import com.example.new_tourslate.ui.signup.SignupActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {

            btnLogin.setOnClickListener {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                if (email.isEmpty()){
                    etEmail.error = "Email is required"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.error = "Invalid Email"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (password.isEmpty() || password.length < 8){
                    etPassword.error = "Password must be more than 8 characters"
                    etPassword.requestFocus()
                    return@setOnClickListener
                }

                loginUser(email, password)
            }

            btnSignup.setOnClickListener {
                Intent(this@LoginActivity, SignupActivity::class.java).also {
                    startActivity(it)
                }
            }

            forgotPassword.setOnClickListener {
                Intent(this@LoginActivity, ForgotPasswordActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this@LoginActivity, MainActivity::class.java).also {
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@LoginActivity, MainActivity::class.java).also { homeIntent ->
                homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(homeIntent)
            }
        }
    }
}
