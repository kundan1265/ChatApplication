package com.example.chatapplication

import android.content.Context
import android.location.OnNmeaMessageListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context , val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SEND = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if(viewType == 1){
            // inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive , parent , false)
            return ReceiveViewHolder(view)
        }
        else{
            // inflate send
            val view: View = LayoutInflater.from(context).inflate(R.layout.send , parent , false)
            return SendViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentMessage = messageList[position]
        if(holder.javaClass == SendViewHolder::class.java){
           // do the stuff for sent view holder
            val viewHolder = holder as SendViewHolder
            holder.sendMessage.text = currentMessage.message
        }
        else{
            // do stuff for receive view holder
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SEND
        }
        else{
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
       return messageList.size
    }
    class SendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
           val sendMessage = itemView.findViewById<TextView>(R.id.text_send_message)
    }
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.text_receive_message)
    }

}