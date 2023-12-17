package com.kalpeet.khatta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kalpeet.khatta.databinding.ActivityLoginEmployerBinding

class LoginActivityEmployer : AppCompatActivity() {
    private lateinit var binding: ActivityLoginEmployerBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmployerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Login Page"
        binding.textViewForgotPassword1.setOnClickListener {
            val intent = Intent(this@LoginActivityEmployer, ForgetActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSignin1.setOnClickListener {
            val intent = Intent(this@LoginActivityEmployer, SignUpEmployer::class.java)
            startActivity(intent)
        }
        binding.buttonLogin1.setOnClickListener {
            val userEmail = binding.editTextUsername1.text.toString()
            val userPassword = binding.editTextPassword1.text.toString()
            signInWithFirebase(userEmail, userPassword)
        }
    }

    private fun signInWithFirebase(userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login is Successful", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@LoginActivityEmployer, Employer_Page::class.java)
                    startActivity(intent)
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