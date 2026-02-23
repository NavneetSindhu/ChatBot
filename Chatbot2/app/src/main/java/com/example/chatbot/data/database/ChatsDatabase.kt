package com.example.chatbot.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chatbot.ui.chats.ChatHistory
import com.example.chatbot.ui.chats.MessageHistory


@Database(
    entities = [ChatHistory::class, MessageHistory::class],
    version = 1,
    exportSchema = false
)
abstract class ChatsDatabase : RoomDatabase(){
    abstract fun chatDao(): ChatsDao

    companion object{
        @Volatile
        private var INSTANCE: ChatsDatabase?=null

        fun getDatabase(context: Context): ChatsDatabase{
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatsDatabase::class.java,
                    "chats_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}