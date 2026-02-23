package com.example.chatbot.data.model

// --- REQUEST MODELS (These are correct and unchanged) ---
data class ChatGptRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)

// --- RESPONSE MODELS (Modified to match OpenRouter) ---
data class ChatGptResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage?
)

data class Choice(
    val index: Int,
    val message: Message,
    val finish_reason: String?
)

data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)