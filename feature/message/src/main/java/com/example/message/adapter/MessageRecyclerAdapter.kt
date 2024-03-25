package com.example.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.extension.loadImagesWithGlide
import com.example.message.databinding.MessageItemIncomingBinding
import com.example.message.databinding.MessageItemOutgoingBinding
import com.example.message.model.Message
import com.example.message.model.Users

class MessageRecyclerAdapter(private val currentUserId: String, private val friend: Users) :
    ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffCallBack()) {

    companion object {
        private const val VIEW_TYPE_INCOMING = 1
        private const val VIEW_TYPE_OUTGOING = 2
    }

    override fun getItemViewType(position: Int): Int {
        val message = getItem(position)
        return if (message.senderId == currentUserId) VIEW_TYPE_OUTGOING else VIEW_TYPE_INCOMING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_INCOMING -> IncomingMessageViewHolder(
                MessageItemIncomingBinding.inflate(layoutInflater, parent, false)
            )

            VIEW_TYPE_OUTGOING -> OutgoingMessageViewHolder(
                MessageItemOutgoingBinding.inflate(layoutInflater, parent, false)
            )

            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        when (holder) {
            is IncomingMessageViewHolder -> holder.bind(message)
            is OutgoingMessageViewHolder -> holder.bind(message)
        }
    }

    inner class IncomingMessageViewHolder(private val binding: MessageItemIncomingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.apply {
                tvMessage.text = message.messageText
                message.timestamp?.let {
                    val dateFormat =
                        java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                    tvTimestamp.text = dateFormat.format(java.util.Date(it))
                }
                ivAvatar.loadImagesWithGlide(friend.avatar)
            }
        }
    }

    inner class OutgoingMessageViewHolder(private val binding: MessageItemOutgoingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.apply {
                tvMessage.text = message.messageText
                message.timestamp?.let {
                    val dateFormat =
                        java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                    tvTimestamp.text = dateFormat.format(java.util.Date(it))
                }
            }
        }
    }
}
