package com.example.chatbot.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitInstance{
    private const val GEMINI_BASE_URL = "https://generativelanguage.googleapis.com/"
    private const val CHATGPT_BASE_URL = "https://openrouter.ai/api/v1/chat/"
    const val GEMINI_API_KEY = "AIzaSyDsEjUH14QGHeKmxmhX2ABAa7NppS44PK4"
    const val GPT_API_KEY = "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"
    private val client = OkHttpClient.Builder()
        // 👇 ADD THESE LINES to increase the timeout to 60 seconds
        .connectTimeout(60, TimeUnit.SECONDS) // Time to establish a connection
        .readTimeout(60, TimeUnit.SECONDS)    // Time to wait for data
        .writeTimeout(60, TimeUnit.SECONDS)   // Time to send data
        .build()

    val gemini: GeminiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(GEMINI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeminiApiService::class.java)
    }

    val gpt: GptApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CHATGPT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GptApiService::class.java)
    }
}