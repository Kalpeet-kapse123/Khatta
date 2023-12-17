package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kalpeet.khatta.databinding.ActivitySeparateEmployeeDetailBinding

class separate_employee_detail : AppCompatActivity() {
    private lateinit var binding: ActivitySeparateEmployeeDetailBinding
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeparateEmployeeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Update Data"
        getAndSetData()
        binding.buttonAdd.setOnClickListener {
            updateData()
        }
    }

    private fun updateData() {
        val updatedName = binding.editTextName.text.toString()
        val updatedAge = binding.editTextAge.text.toString().toInt()
        val updatedEmail = binding.editTextEmail.text.toString()
        val updatedMobile = binding.editTextMobile.text.toString()
        val updatedDesignation = binding.editTextDesignation.text.toString()
        val updatedSalary = binding.editTextSalary.text.toString().toInt()
        val userId = intent.getStringExtra("id").toString()
        val userMap = mutableMapOf<String, Any>()
        userMap["userId"] = userId
        userMap["userName"] = updatedName
        userMap["userAge"] = updatedAge
        userMap["userEmail"] = updatedEmail
        userMap["userMobile"] = updatedMobile
        userMap["userDesignation"] = updatedDesignation
        userMap["userSalary"] = updatedSalary
        myReference.child(userId).updateChildren(userMap).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "The user data has been updated.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }

        }
    }

    private fun getAndSetData() {
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0).toString()
        val email = intent.getStringExtra("email")
        val mobile = intent.getStringExtra("mobile")
        val designation = intent.getStringExtra("designation")
        val salary = intent.getIntExtra("salary", 0).toString()
        binding.editTextName.setText(name)
        binding.editTextAge.setText(age)
        binding.editTextEmail.setText(email)
        binding.editTextMobile.setText(mobile)
        binding.editTextDesignation.setText(designation)
        binding.editTextSalary.setText(salary)
    }
}