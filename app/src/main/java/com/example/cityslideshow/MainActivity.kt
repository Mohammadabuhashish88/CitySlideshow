
package com.example.cityslideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CitySlideshow()
            }
        }
    }
}

@Composable
fun CitySlideshow() {
    val images = listOf(
        R.drawable.newyork,
        R.drawable.amman,
        R.drawable.dubai,
        R.drawable.tokyo,
        R.drawable.paris
    )

    val captions = listOf(
        "New York City, United States",
        "Amman, Jordan",
        "Dubai, United Arab Emirates",
        "Tokyo, Japan",
        "Paris, France"
    )

    var currentImage by remember { mutableIntStateOf(0) }
    var imageNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cities Around the World",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = images[currentImage]),
            contentDescription = captions[currentImage],
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = captions[currentImage],
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Image ${currentImage + 1} of ${images.size}"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                currentImage =
                    (currentImage - 1 + images.size) % images.size
                errorMessage = ""
            }) {
                Text("Back")
            }

            Button(onClick = {
                currentImage = (currentImage + 1) % images.size
                errorMessage = ""
            }) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = imageNumber,
            onValueChange = {
                imageNumber = it
                errorMessage = ""
            },
            label = { Text("Enter image number (1-5)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val number = imageNumber.toIntOrNull()

            if (number != null && number in 1..images.size) {
                currentImage = number - 1
                errorMessage = ""
            } else {
                errorMessage = "Enter a number from 1 to 5"
            }
        }) {
            Text("Go")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
