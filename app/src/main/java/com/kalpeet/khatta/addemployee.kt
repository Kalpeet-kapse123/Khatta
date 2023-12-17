package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kalpeet.khatta.databinding.ActivityAddemployeeBinding

class addemployee : AppCompatActivity() {
    private lateinit var binding: ActivityAddemployeeBinding
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddemployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Add Employee"
        binding.buttonAdd.setOnClickListener {
            addUserToDatabase()
        }

    }

    private fun addUserToDatabase() {
        val name: String = binding.editTextName.text.toString()
        val age: Int = binding.editTextAge.text.toString().toInt()
        val email: String = binding.editTextEmail.text.toString()
        val mobile: String = binding.editTextMobile.text.toString()
        val designation: String = binding.editTextDesignation.text.toString()
        val salary: Int = binding.editTextSalary.text.toString().toInt()
        val id: String = myReference.push().key.toString()
        val user = Users(id, name, age, email, mobile, designation, salary)
        myReference.child(id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "The new employee ahs been added to company.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception.toString(), Toast.LENGTH_SHORT)
                    .show()

            }

        }
    }
}