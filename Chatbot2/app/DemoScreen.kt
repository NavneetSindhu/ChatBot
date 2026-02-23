import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp




@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        // Welcome Text
        Text(
            text = "Welcome to",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "A World Of Wonders",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, bottom = 16.dp)
        )

        // Search Bar
        SearchBar()

        // Horizontal Category Chips
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(listOf("Sculpture", "Classic", "Sanctuary")) { category ->
                Chip(text = category)
            }
        }

        // Vertical List of Museum Cards
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(sampleMuseums) { museum ->
                MuseumCard(museum)
            }
        }
    }
}


data class Museum(
    val name: String,
    val location: String,
    val imageUrl: String,
    val tags: List<String> = emptyList()
)

val sampleMuseums = listOf(
    Museum(
        name = "Gipsoteca Anto Museum",
        location = "Treviso, Italy",
        imageUrl = "https://example.com/gipsoteca_image.jpg" // Replace with actual image URL
    ),
    Museum(
        name = "Radya Pustaka Museum",
        location = "Surakarta, Indonesia",
        imageUrl = "https://example.com/radya_image.jpg", // Replace with actual image URL
        tags = listOf("Artifacts", "History", "Javanese")
    )
    // Add more museums here
)


@Composable
fun MuseumCard(museum: Museum) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Adjust height as needed
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = rememberImagePainter(data = museum.imageUrl), // Requires Coil or similar library
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Optional tags row
            if (museum.tags.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    museum.tags.forEach { tag ->
                        Chip(text = tag)
                    }
                }
            }

            // Museum details
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.8f)) // Semi-transparent background
                    .padding(16.dp)
            ) {
                Text(text = museum.name, style = MaterialTheme.typography.h6)
                Text(text = museum.location, style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Surface(
        color = Color.Gray.copy(alpha = 0.5f),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(end = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}



@Composable
fun SearchBar() {
    TextField(
        value = "", // You would use a state variable here
        onValueChange = { /* Handle input change */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Search destination...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        trailingIcon = { Icon(Icons.Default.FilterList, contentDescription = "Filter Icon") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray.copy(alpha = 0.3f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp)
    )
}