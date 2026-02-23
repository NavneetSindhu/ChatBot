package com.example.chatbot.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.data.model.Message
import com.example.chatbot.data.repository.ChatRepository
import com.example.chatbot.ui.chats.ChatHistory
import com.example.chatbot.ui.chats.MessageHistory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File

class ChatViewModel(private val chatRepo: ChatRepository) : ViewModel() {

    val currentChatId = MutableStateFlow<Int?>(null)

    private val _messages = MutableStateFlow<List<MessageHistory>>(emptyList())
    val messages: StateFlow<List<MessageHistory>> = _messages


    val allChats : StateFlow<List<ChatHistory>> = chatRepo.getAllChats().stateIn(viewModelScope,
        SharingStarted.Lazily,emptyList())


    fun loadMessages(chatId: Int) {
        viewModelScope.launch {
            chatRepo.getMessagesForChat(chatId).collect { msgs ->
                _messages.value = msgs
            }
        }
    }




    fun shareFullChat(chatId: Int, onResult: (String) -> Unit) {
        viewModelScope.launch {
            val messages = chatRepo.getMessagesForChat(chatId).first() // collect once
            val chatText = messages.joinToString("\n") { "${it.isUser}: ${it.msg}" }
            onResult(chatText)
        }
    }

    // Entire conversation history
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMessagesForChatFlow(chatId: Int): Flow<List<MessageHistory>> {
        return chatRepo.getMessagesForChat(chatId)

    }

    fun setCurrentChat(chatId: Int) {
        currentChatId.value = chatId
    }

    // Image context (not shown in UI directly)
    var imageContext by mutableStateOf<String?>(null)

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()



    fun newChat(title:String){
        viewModelScope.launch {
           val id = chatRepo.insertChat(chat = ChatHistory(title = title))
            currentChatId.value = id.toInt()
        }

    }



    fun chatsBySearch(q:String) : StateFlow<List<ChatHistory>> = chatRepo.getMsgBySearch(q).stateIn(viewModelScope,
        SharingStarted.Lazily,emptyList())

fun deleteChat(id:Int){

    viewModelScope.launch {
        chatRepo.deleteChat(id)
    }

}



    fun uploadImage(file: File) {
        val chatId = currentChatId.value ?: return
        viewModelScope.launch {
            // Add user's image to the chat immediately
            chatRepo.insertMessage(
                MessageHistory(chatId = currentChatId.value!!, isUser = true, imgUri = file.toUri().toString())
            )
            _loading.value = true
            try {
                val response = chatRepo.uploadImageToGemini(file)


                imageContext = response.candidates.firstOrNull()
                    ?.content?.parts?.firstOrNull()?.text

                // Show a confirmation or error message
                val systemMessage = if (imageContext != null) {
                    "Image received! You can now ask questions about it."
                } else {
                    "Error: Could not understand the image."
                }

                chatRepo.insertMessage(MessageHistory(chatId = currentChatId.value!!,msg=systemMessage, isUser = false))

            } catch (e: Exception) {
                chatRepo.insertMessage(MessageHistory(chatId = currentChatId.value!!,msg="Error: ${e.message}", isUser = false))
            } finally {
                _loading.value = false
            }
        }
    }

    fun sendMessage(question: String) {
        if (question.isBlank()) return
        val chatId = currentChatId.value ?: return

        // Show user’s question immediately
        viewModelScope.launch {
            chatRepo.insertMessage(MessageHistory(chatId = currentChatId.value!!,msg=question, isUser = true))

        }

        _loading.value = true
        val context = imageContext

        viewModelScope.launch {
            try {
                if (context == null) {
                    // No image context → send directly to Gemini
                    val ans = chatRepo.sendMsgToGemini(question)
                    chatRepo.insertMessage(MessageHistory(chatId = currentChatId.value!!,msg=
                        ans.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
                            .orEmpty(),
                        isUser = false
                    ))
                } else {
                    // GPT handles follow-up questions
                    val response = chatRepo.askGpt(question, context)
                    val aiResponseText = response.choices
                        .firstOrNull()?.message?.content
                        .orEmpty()

                    chatRepo.insertMessage(MessageHistory(chatId = currentChatId.value!!,msg=aiResponseText, isUser = false))
                }
            } catch (e: Exception) {
                // Fallback if GPT call fails
                try {
                    val ans = chatRepo.sendMsgToGemini(question)
                    chatRepo.insertMessage(MessageHistory(chatId = currentChatId.value!!,msg=
                        ans.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
                            .orEmpty(),
                        isUser = false
                    ))
                } catch (inner: Exception) {
                    chatRepo.insertMessage(MessageHistory(chatId = currentChatId.value!!,msg=
                        "Error: ${inner.message}",
                        isUser = false
                    ))
                }
            } finally {
                _loading.value = false
            }
        }
    }
}
