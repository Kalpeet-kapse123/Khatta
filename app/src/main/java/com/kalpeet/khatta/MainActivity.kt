package com.kalpeet.khatta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.kalpeet.khatta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardViewEmployer.setOnClickListener {
            val intent= Intent(this@MainActivity,LoginActivityEmployer::class.java)
            startActivity(intent)
        }
        binding.cardViewEmployee.setOnClickListener {
            val intent= Intent(this@MainActivity,LoginActivityEmployee::class.java)
            startActivity(intent)
        }



    }
}