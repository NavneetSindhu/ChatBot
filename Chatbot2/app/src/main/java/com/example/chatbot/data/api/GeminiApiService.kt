package com.example.chatbot.data.api

import com.example.chatbot.data.model.GeminiRequest
import com.example.chatbot.data.model.GeminiResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface GeminiApiService {
    // It must accept a @Body of type GeminiRequest
    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    suspend fun uploadImage( // This name can stay, but what it accepts must change
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse

    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    suspend fun sendMsg(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ) : GeminiResponse
}