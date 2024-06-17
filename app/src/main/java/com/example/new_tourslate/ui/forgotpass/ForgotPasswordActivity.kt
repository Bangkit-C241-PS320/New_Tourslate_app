package com.example.new_tourslate.ui.forgotpass

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.new_tourslate.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            btnResetPassword.setOnClickListener {
                val email = etEmail.text.toString().trim()

                if (email.isEmpty()) {
                    etEmail.error = "Email is required"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.error = "Invalid Email"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@ForgotPasswordActivity, "Link to reset your password has been sent to your email", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@ForgotPasswordActivity, "Failed to send email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}