package com.kalpeet.khatta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kalpeet.khatta.databinding.ActivityLoginEmployeeBinding

class LoginActivityEmployee : AppCompatActivity() {
    private lateinit var binding: ActivityLoginEmployeeBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Login Page"
        binding.textViewForgotPassword.setOnClickListener {
            val intent = Intent(this@LoginActivityEmployee, ForgetActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSignin.setOnClickListener {
            val intent = Intent(this@LoginActivityEmployee, SignupEmployee::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener {
            val userEmail = binding.editTextUsername.text.toString()
            val userPassword = binding.editTextPassword.text.toString()
            signInWithFirebase(userEmail, userPassword)
        }
    }

    private fun signInWithFirebase(userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login is Successful", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@LoginActivityEmployee, Employee_Page::class.java)
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