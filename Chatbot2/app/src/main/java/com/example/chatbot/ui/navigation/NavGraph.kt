package com.example.chatbot.ui.navigation

import SettingsScreen
import com.example.chatbot.ui.viewmodel.ThemeViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chatbot.ui.screens.ChatScreen
import com.example.chatbot.ui.screens.HomeScreen
import com.example.chatbot.ui.screens.ProfileScreen
import com.example.chatbot.ui.viewmodel.ChatViewModel

@Composable
fun NavGraph(navController: NavHostController,viewModel: ChatViewModel,themeViewModel: ThemeViewModel) {
    NavHost(
        navController=navController,
        startDestination = Destinations.HOME
    ){
        composable(Destinations.HOME) { HomeScreen(viewModel,navController)  }
        composable(
            route = Destinations.CHAT_WITH_ARG,
            arguments = listOf(navArgument("defaultText") { defaultValue = "" })
        ) { backStackEntry ->
            val defaultText = backStackEntry.arguments?.getString("defaultText") ?: ""
            ChatScreen(viewModel, navController, defaultText)
        }

        composable(Destinations.PROFILE) { ProfileScreen()  }
        composable(Destinations.SETTINGS) { SettingsScreen(navController, themeViewModel)  }
    }
}