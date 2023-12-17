package com.kalpeet.khatta

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalpeet.khatta.databinding.ActivityDeleteEmployeeBinding
import com.kalpeet.khatta.databinding.UsersItemBinding

class DeleteAdapter(
    var context: Context,
    var userList: ArrayList<Users>
) : RecyclerView.Adapter<DeleteAdapter.DeleteViewHolder>() {
    inner class DeleteViewHolder(val adapterBinding: UsersItemBinding) :
        RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeleteViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: DeleteViewHolder, position: Int) {
        holder.adapterBinding.textViewName.text = userList[position].userName
        holder.adapterBinding.textViewPost.text = userList[position].userDesignation
        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, delete_employee::class.java)
            intent.putExtra("id", userList[position].userId)
            intent.putExtra("name", userList[position].userName)
            intent.putExtra("age", userList[position].userAge)
            intent.putExtra("email", userList[position].userEmail)
            intent.putExtra("mobile", userList[position].userMobile)
            intent.putExtra("designation", userList[position].userDesignation)
            intent.putExtra("salary", userList[position].userSalary)
            context.startActivity(intent)

        }

    }

    fun getUserId(position: Int): String {
        return userList[position].userId
    }
}
