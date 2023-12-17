package com.kalpeet.khatta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.kalpeet.khatta.databinding.ActivityEmployerPageBinding

class Employer_Page : AppCompatActivity() {
    lateinit var binding: ActivityEmployerPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployerPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Employer Page"
        binding.cardViewAddEmployee.setOnClickListener {
            val intent = Intent(this@Employer_Page, addemployee::class.java)
            startActivity(intent)
        }
        binding.cardViewDeleteEmployee.setOnClickListener {
            val intent = Intent(this@Employer_Page, delete_employee::class.java)
            startActivity(intent)
        }
        binding.cardViewEmployeeDetails.setOnClickListener {
            val intent = Intent(this@Employer_Page, EmployeesDetails::class.java)
            startActivity(intent)
        }
        binding.cardViewExpenses.setOnClickListener {
            val intent = Intent(this@Employer_Page, companyexpenses::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_file, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Sign_out) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@Employer_Page, LoginActivityEmployer::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}