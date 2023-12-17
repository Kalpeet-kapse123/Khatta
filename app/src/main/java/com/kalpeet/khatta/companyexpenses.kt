package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class companyexpenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_companyexpenses)
        title = "Total Expenses"
    }
}