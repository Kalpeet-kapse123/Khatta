package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kalpeet.khatta.databinding.ActivityEmployeesDetailsBinding

class EmployeesDetails : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeesDetailsBinding
    private val database:FirebaseDatabase=FirebaseDatabase.getInstance()
    private val myReference:DatabaseReference=database.reference.child("Users")
    val userList=ArrayList<Users>()
    lateinit var usersAdapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEmployeesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Employees Details"
        retrieveDataFromDatabase()

    }

    private fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for(eachUser in snapshot.children){
                    val user=eachUser.getValue(Users::class.java)
                    if (user!=null){
                        println("UserId:${user.userId}")
                        println("UserName:${user.userName}")
                        println("UserAge:${user.userAge}")
                        println("UserEmail:${user.userEmail}")
                        println("UserMobile:${user.userMobile}")
                        println("UserDesignation:${user.userDesignation}")
                        println("UserSalary:${user.userSalary}")
                        println("*****************************************")
                        userList.add(user)
                    }
                    usersAdapter= UsersAdapter(this@EmployeesDetails,userList)
                    binding.recyclerView.layoutManager=LinearLayoutManager(this@EmployeesDetails)
                    binding.recyclerView.adapter=usersAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}