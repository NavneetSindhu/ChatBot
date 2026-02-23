package com.example.chatbot.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chatbot.R
import com.example.chatbot.ui.CarouselItem
import com.example.chatbot.ui.chats.MessageHistory
import com.example.chatbot.ui.navigation.Destinations
import com.example.chatbot.ui.viewmodel.ChatViewModel
import android.content.Context
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( viewModel: ChatViewModel,navController: NavController,) {
    // Collect all state at the top level

    var chatCounter by rememberSaveable { mutableStateOf(1) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val toShow by if (searchQuery.isNotEmpty()) {
        viewModel.chatsBySearch(searchQuery).collectAsState()
    } else {
        viewModel.allChats.collectAsState()
    }
    val context = LocalContext.current





    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chatbot", fontWeight = FontWeight.SemiBold) },
                actions = {
                    IconButton(onClick = { navController.navigate(Destinations.SETTINGS) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.newChat("Note no. $chatCounter")
                        chatCounter++
                        navController.navigate("chat/")
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Chat Now")
                }
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- Search Bar ---
            item {

                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                                    },
                    placeholder = { Text("Search conversations") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    singleLine = true
                )
            }

            // --- Suggested Topics ---

            item{
                SuggestedTopicsCarousel(viewModel,navController)
            }


            // --- Recent Chats ---
            item {
                Text(
                    text = "Recent Chats",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }




            items(
                toShow
            ) { chat ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(horizontal = 16.dp)
                        .clickable {
                            viewModel.setCurrentChat(chat.id)
                            navController.navigate("chat/")
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
//                            Text(text = "Chat #${chat.id}", style = MaterialTheme.typography.titleMedium)
                            Text(text = chat.title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.weight(1f))


                        IconButton(onClick = {

                            viewModel.shareFullChat(chat.id) {chatText ->
                                context
                                val intent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, chatText)
                                }
                                context.startActivity(
                                    Intent.createChooser(intent, "Share Chat")
                                )


                            }
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "shareChat")
                        }


                        IconButton(onClick = {
                            viewModel.deleteChat(chat.id)
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }

                    }
                }
            }
        }
    }
}





@Composable
fun SuggestedTopicsCarousel(viewModel: ChatViewModel,navController: NavController) {
    val listState = rememberLazyListState() // Remember scroll state


    val items = remember {
        listOf(
            CarouselItem("Ask me anything",R.drawable.boy1),
            CarouselItem("Tell me a story",R.drawable.boy2),
            CarouselItem("Brainstorm an idea",R.drawable.boy3),
            CarouselItem("Learn something new",R.drawable.girl1)

        )
    }
    // Auto-scroll effect
    LaunchedEffect(Unit) {
        while (true) {
            val nextIndex = (listState.firstVisibleItemIndex + 1) % 6
            listState.animateScrollToItem(nextIndex)
            kotlinx.coroutines.delay(3000L) // 3 seconds delay between scrolls
        }
    }

    Column {
        // Title
        Text(
            text = "Suggested Topics",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Carousel
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { index ->
                Card(
                    modifier = Modifier.size(150.dp, 180.dp).clickable{
                        navController.navigate("chat/${index.text}")
                        viewModel.newChat(title = index.text)
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = index.imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = index.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}




