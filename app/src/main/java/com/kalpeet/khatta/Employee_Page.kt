package com.kalpeet.khatta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kalpeet.khatta.databinding.ActivityEmployeePageBinding
import com.kalpeet.khatta.databinding.ActivitySalrySlipBinding

class Employee_Page : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Employee Page"
        binding.cardViewGenerateSlip.setOnClickListener {
            val intent = Intent(this@Employee_Page, salry_slip::class.java)
            startActivity(intent)
        }


    }
}