package com.example.chatbot.data.model

// --- REQUEST MODELS ---

data class GeminiRequest(
    val contents: List<Content>
)

data class Content(
    val parts: List<Part>
)

sealed interface Part

data class TextPart(val text: String) : Part

data class ImagePart(
    val inlineData: InlineData
) : Part

data class InlineData(
    val mimeType: String,
    val data: String
)

// --- RESPONSE MODELS ---

data class GeminiResponse(
    val candidates: List<Candidate>
)

data class Candidate(
    val content: ResponseContent
)

data class ResponseContent(
    val parts: List<ResponsePart>,
    val role: String
)

data class ResponsePart(
    val text: String
)