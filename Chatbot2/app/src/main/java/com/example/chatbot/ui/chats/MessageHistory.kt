package com.example.chatbot.ui.chats

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = ChatHistory::class,
            parentColumns = ["id"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MessageHistory(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    val chatId:Int,
    val msg:String? = null,
    val isUser: Boolean,
    val imgUri: String? = null
)