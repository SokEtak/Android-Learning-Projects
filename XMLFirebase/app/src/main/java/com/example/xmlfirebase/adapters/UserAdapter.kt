package com.example.xmlfirebase.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlfirebase.models.User
import com.example.xmlfirebase.activities.UpdateUserActivity
import com.example.xmlfirebase.databinding.UserItemBinding

//this might be different from another RecyclerView lesson
class UserAdapter(
    var context: Context,
    private var fullList: List<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var filteredList: List<User> = fullList.toList()

    inner class UserViewHolder(val adapterBinding: UserItemBinding) :
        RecyclerView.ViewHolder(adapterBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = filteredList[position]
        holder.adapterBinding.tvNameItem.text = user.userName
        holder.adapterBinding.tvAgeItem.text = user.userAge.toString()
        holder.adapterBinding.tvEmailItem.text = user.userEmail

        //prepare for passing data to Update Activity when clicking on individual user
        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, UpdateUserActivity::class.java).apply {
                putExtra("id", user.userId)
                putExtra("name", user.userName)
                putExtra("age", user.userAge)
                putExtra("email", user.userEmail)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    //for recyclerView Search
    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            fullList
        } else {
            fullList.filter {
                it.userName.contains(query, ignoreCase = true) ||
                        it.userEmail.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    fun updateList(newList: List<User>) {
        fullList = newList
        filteredList = newList
        notifyDataSetChanged()
    }
}