package com.darasoylu.chatapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darasoylu.chatapp.view.ChatActivity
import com.darasoylu.chatapp.data.User
import com.darasoylu.chatapp.databinding.UserLayoutBinding

class UserAdapter(private val context: Context, private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.binding.textName.text = userList[position].name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", userList[position].name)
            intent.putExtra("uid", userList[position].uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(val binding: UserLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}