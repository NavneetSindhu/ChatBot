import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chatbot.ui.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController,themeViewModel: ThemeViewModel) {
    val isDarkThemeEnabled by themeViewModel.isDarkTheme.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Preferences Section
            Text(
                text = "PREFERENCES",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Card(shape = RoundedCornerShape(16.dp)) {
                SettingsItem(
                    title = "Dark Theme",
                    icon = { Icon(Icons.Default.DarkMode, contentDescription = "Dark Theme") },
                    control = {
                        Switch(
                            checked = isDarkThemeEnabled,
                            onCheckedChange = { themeViewModel.toggleTheme() }
                        )
                    }
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(
                    title = "Notifications",
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
                    control = {
                        Icon(Icons.Default.ChevronRight, contentDescription = "Navigate")
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // More Section
            Text(
                text = "MORE",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Card(shape = RoundedCornerShape(16.dp)) {
                SettingsItem(
                    title = "About",
                    icon = { Icon(Icons.Default.Info, contentDescription = "About") },
                    control = {
                        Icon(Icons.Default.ChevronRight, contentDescription = "Navigate")
                    }
                )
            }
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    icon: @Composable () -> Unit,
    control: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            icon()
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 16.sp)
        }
        control()
    }
}

