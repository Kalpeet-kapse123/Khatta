package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kalpeet.khatta.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Forget Password"
        binding.buttonReset.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "We sent a password reset mail to your email id provided.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        task.exception?.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}