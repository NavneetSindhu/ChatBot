package com.example.chatbot.ui.chats

import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "chats_history")


data class ChatHistory(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String
)
