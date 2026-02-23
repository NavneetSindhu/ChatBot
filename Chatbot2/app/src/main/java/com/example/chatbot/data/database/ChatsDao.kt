package com.example.chatbot.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.chatbot.ui.chats.ChatHistory
import com.example.chatbot.ui.chats.MessageHistory
import kotlinx.coroutines.flow.Flow


@Dao
interface ChatsDao {

    @Insert
    suspend fun addChat(chat: ChatHistory): Long

    @Query("UPDATE chats_history SET title = :title WHERE id = :id")
    suspend fun updateChatTitle(id: Int, title: String)

    @Insert
    suspend fun addMsg(msg: MessageHistory)

    @Delete
    suspend fun deleteChat(chat: ChatHistory)

    @Query("SELECT * FROM chats_history ORDER BY id DESC")
     fun getAllChats(): Flow<List<ChatHistory>>

    @Query("SELECT * FROM messages WHERE chatId= :chatId ORDER BY id ASC")
     fun getAllMessages(chatId:Int):Flow<List<MessageHistory>>

    @Query("SELECT * FROM chats_history WHERE title LIKE '%' || :keyword || '%' ORDER BY title ASC")
    fun getMessagesFromSearch(keyword: String): Flow<List<ChatHistory>>


}