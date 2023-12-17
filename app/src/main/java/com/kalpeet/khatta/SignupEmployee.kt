package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import com.kalpeet.khatta.databinding.ActivityMainBinding
import com.kalpeet.khatta.databinding.ActivitySignupEmployeeBinding

class SignupEmployee : AppCompatActivity() {
    private lateinit var binding: ActivitySignupEmployeeBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupEmployeeBinding.inflate(layoutInflater)
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
                val database = Firebase.database
                Log.d("TAG", database.toString())
                val myRef = database.getReference("Users")

                myRef.child(auth.currentUser?.uid.toString()).setValue(userEmail)

                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }
}