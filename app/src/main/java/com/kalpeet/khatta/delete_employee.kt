package com.kalpeet.khatta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kalpeet.khatta.databinding.ActivityDeleteEmployeeBinding

class delete_employee : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteEmployeeBinding
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("Users")
    val userList = ArrayList<Users>()
    lateinit var usersAdapter: DeleteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Remove Employee"
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                val id = usersAdapter.getUserId(viewHolder.adapterPosition)
                myReference.child(id).removeValue()
                Toast.makeText(applicationContext, "The user has been deleted.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        ).attachToRecyclerView(binding.recyclerView)
        retrieveDataFromDatabase()

    }

    private fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (eachUser in snapshot.children) {
                    val user = eachUser.getValue(Users::class.java)
                    if (user != null) {
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
                    usersAdapter = DeleteAdapter(this@delete_employee, userList)
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@delete_employee)
                    binding.recyclerView.adapter = usersAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
