package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kalpeet.khatta.databinding.ActivitySignUpEmployerBinding

class SignUpEmployer : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpEmployerBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpEmployerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSignin.setOnClickListener {
            val userEmail = binding.editTextUsername.text.toString()
            val userPassword = binding.editTextPassword.text.toString()
            signUpWithFirebase(userEmail, userPassword)
        }
        title = "Sign In"
    }

    private fun signUpWithFirebase(userEmail: String, userPassword: String) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "Your account has been created.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }
}