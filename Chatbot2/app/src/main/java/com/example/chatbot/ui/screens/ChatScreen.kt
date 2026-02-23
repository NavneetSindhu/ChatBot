package com.example.chatbot.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.chatbot.R
import com.example.chatbot.ui.viewmodel.ChatViewModel
import com.example.chatbot.ui.chats.MessageHistory
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(viewModel: ChatViewModel, navController: NavController,defaultText:String) {
    // Collect messages from the ViewModel's public StateFlow
    val messageList: List<MessageHistory> by viewModel.messages.collectAsState()
    val currentChatId by viewModel.currentChatId.collectAsState()

    val context = LocalContext.current
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val loading by viewModel.loading.collectAsState()
    var userInput by remember { mutableStateOf(TextFieldValue(defaultText)) }

    // Load messages when the currentChatId changes
    LaunchedEffect(currentChatId) {
        currentChatId?.let { chatId ->
            viewModel.loadMessages(chatId)
        }
    }

    LaunchedEffect(messageList.size) {
        if (messageList.isNotEmpty()) {
            listState.animateScrollToItem(messageList.size - 1)
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            TopAppBar(
                title = { Text("New Chat") },
                navigationIcon = {
                    IconButton(onClick = {
                         // Add this call
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Close chat")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri: Uri? ->
                    uri?.let {
                        val file = uriToFile(uri, context)
                        if (file != null) viewModel.uploadImage(file)
                    }
                }

                IconButton(onClick = { launcher.launch("image/*") }) {
                    Icon(
                        Icons.Default.ImageSearch,
                        contentDescription = "Attach image",
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    placeholder = { Text("Ask something...") },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (userInput.text.isNotBlank()) {
                            viewModel.sendMessage(userInput.text.trim())
                            userInput = TextFieldValue("")
                            // It's good practice to ensure messageList is updated before scrolling
                            // However, the LaunchedEffect(messageList.size) should handle this.
                            // Consider if explicit scroll here is needed or if it conflicts.
                            scope.launch {
                                // listState.animateScrollToItem(messageList.size) // Potentially redundant
                            }
                        }
                    }
                ) {
                    Text("Send")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messageList.size) { index ->
                val msg = messageList[index]
                ChatBubble(message = msg)
            }

            if (loading) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TypingIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun TypingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(32.dp), // size of the spinner
        color = MaterialTheme.colorScheme.primary, // M3 primary color
        strokeWidth = 3.dp // thickness of the spinner
    )
}


@Composable
fun ChatBubble(message: MessageHistory) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!message.isUser) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray)
            ) {
                Image(
                    painterResource(R.drawable.chat_logo),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }

        Surface(
            color = if (message.isUser) MaterialTheme.colorScheme.secondary else Color.LightGray,
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 2.dp,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            if (message.imgUri != null) {
                Box(
                    contentAlignment = Alignment.CenterEnd,

                ) {
                    SubcomposeAsyncImage(
                        model = message.imgUri,
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        loading = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(150.dp)
                            ) {
                                CircularProgressIndicator()
                            }
                        },
                        error = {
                            Icon(
                                Icons.Default.BrokenImage,
                                contentDescription = "Error loading image"
                            )
                        },
                        contentScale = ContentScale.Crop
                    )
                }
            } else {
                Text(
                    text = message.msg ?: "error fetching",
                    modifier = Modifier.padding(12.dp),
                    color = if (message.isUser) Color.White else Color.Black,
                    textAlign = TextAlign.Start
                )
            }
        }

        if (message.isUser) {
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray)
            ) {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

fun uriToFile(uri: Uri, context: Context): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val tempFile =
            File(context.cacheDir, "picked_image_${System.currentTimeMillis()}.jpg")
        inputStream.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        tempFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
