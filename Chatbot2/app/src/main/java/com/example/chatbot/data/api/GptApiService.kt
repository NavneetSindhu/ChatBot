package com.example.chatbot.data.api


import com.example.chatbot.data.model.ChatGptRequest
import com.example.chatbot.data.model.ChatGptResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptApiService {

    @Headers("Authorization: Bearer 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")
    @POST("completions")
    suspend fun sendMessage(
        @Body request: ChatGptRequest
    ): ChatGptResponse

}