package com.darasoylu.chatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darasoylu.chatapp.data.Message
import com.darasoylu.chatapp.databinding.ReceiveBinding
import com.darasoylu.chatapp.databinding.SentBinding
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(private val context: Context, private val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_RECEIVE = 1
    private val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            ReceiveViewHolder(ReceiveBinding.inflate(LayoutInflater.from(context), parent, false))
        } else {
            SentViewHolder(SentBinding.inflate(LayoutInflater.from(context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.javaClass == SentViewHolder::class.java) {
            //sent
            holder as SentViewHolder
            holder.setBinding.textSentMessage.text = messageList[position].message
        } else {
            //receive
            holder as ReceiveViewHolder
            holder.receivebinding.textSentMessage.text = messageList[position].message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (FirebaseAuth.getInstance().currentUser?.uid.equals(messageList[position].senderId)) {
            ITEM_SENT
        } else {
            ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(val setBinding: SentBinding) : RecyclerView.ViewHolder(setBinding.root)

    class ReceiveViewHolder(val receivebinding: ReceiveBinding) : RecyclerView.ViewHolder(receivebinding.root)

}