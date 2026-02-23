package com.example.chatbot


import com.example.chatbot.ui.viewmodel.ThemeViewModel
import com.example.chatbot.ui.viewmodel.ChatViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.chatbot.data.api.RetrofitInstance
import com.example.chatbot.data.database.ChatsDatabase
import com.example.chatbot.data.repository.ChatRepository
import com.example.chatbot.ui.navigation.NavGraph
import com.example.chatbot.ui.viewmodel.ChatViewModelFactory
import com.example.compose.ChatbotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val api by lazy {
            RetrofitInstance.gemini
        }
        val gpt by lazy {
            RetrofitInstance.gpt
        }
        val themeViewModel by viewModels<ThemeViewModel>()
        val dao = ChatsDatabase.getDatabase(applicationContext).chatDao()
        val repository = ChatRepository(api,gpt,dao)
        val factory = ChatViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ChatViewModel::class.java]


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme = themeViewModel.isDarkTheme.collectAsState()
            val navController = rememberNavController()
            ChatbotTheme(darkTheme = isDarkTheme.value||isSystemInDarkTheme()) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(navController,viewModel,themeViewModel)
                }
            }
        }
    }
}


@Composable
fun MainScreen(){

}

