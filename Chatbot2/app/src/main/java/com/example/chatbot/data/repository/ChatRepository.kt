package com.example.chatbot.data.repository

import com.example.chatbot.data.model.ChatGptRequest
import com.example.chatbot.data.model.ChatGptResponse
import com.example.chatbot.data.model.Message
import com.example.chatbot.data.api.GeminiApiService
import com.example.chatbot.data.api.GptApiService
import com.example.chatbot.data.model.Content
import com.example.chatbot.data.model.GeminiRequest
import com.example.chatbot.data.model.GeminiResponse
import com.example.chatbot.data.model.ImagePart
import com.example.chatbot.data.model.InlineData
import com.example.chatbot.data.model.TextPart
import java.io.File
import android.util.Base64
import com.example.chatbot.data.database.ChatsDao
import com.example.chatbot.ui.chats.ChatHistory
import com.example.chatbot.ui.chats.MessageHistory
import kotlinx.coroutines.flow.Flow

class ChatRepository(
    private val geminiApi: GeminiApiService,
    private val gptApi: GptApiService,
    private val dao: ChatsDao
) {

    suspend fun uploadImageToGemini(
        file: File,
        prompt: String = "Give every possible context about this image"
    ): GeminiResponse {
        // 1. Read the image file into bytes and encode it to a Base64 string.
        val imageBytes = file.readBytes()
        val base64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP)

        // 2. Build the correct GeminiRequest JSON object.
        val request = GeminiRequest(
            contents = listOf(
                Content(
                    parts = listOf(
                        TextPart(prompt), // The text prompt
                        ImagePart(
                            inlineData = InlineData(
                                mimeType = "image/jpeg", // or image/png
                                data = base64Image      // The encoded image string
                            )
                        )
                    )
                )
            )
        )

        // 3. Call the updated API service method with the JSON request.
        val apiKey = "AIzaSyDsEjUH14QGHeKmxmhX2ABAa7NppS44PK4"
        return geminiApi.uploadImage(apiKey, request)
    }


    suspend fun sendMsgToGemini(q:String): GeminiResponse{
        val request = GeminiRequest(
            contents = listOf(
                Content(
                    listOf(
                        TextPart(q)
                    )
                )
            )
        )
        val apiKey = "AIzaSyDsEjUH14QGHeKmxmhX2ABAa7NppS44PK4"
        return geminiApi.sendMsg(apiKey,request)
    }


    // CHATGPT
    suspend fun askGpt(question: String, context: String): ChatGptResponse {

        val messages = listOf(
            Message(
                role = "system",
                content = "You are an assistant that answers questions based on a specific context about an image. The context is: '$context'"
            ),
            Message(
                role = "user",
                content = question
            )
        )

        // 2. Create the request object with the correct 'messages' property.
        val request = ChatGptRequest(
            model = "openai/gpt-oss-20b:free", // Or "gpt-4-turbo", "gpt-3.5-turbo"
            messages = messages
        )

        // 3. Send the request.
        return gptApi.sendMessage(request)
    }


    suspend fun insertChat(chat: ChatHistory): Long = dao.addChat(chat)

     fun getAllChats(): Flow<List<ChatHistory>> = dao.getAllChats()

    suspend fun deleteChat(chatId:Int) = dao.deleteChat(ChatHistory(chatId, title = ""))

    suspend fun insertMessage(msg: MessageHistory) = dao.addMsg(msg)

    fun getMsgBySearch(keyword: String): Flow<List<ChatHistory>> = dao.getMessagesFromSearch(keyword)
     fun getMessagesForChat(chatId: Int): Flow<List<MessageHistory>> = dao.getAllMessages(chatId)
}

