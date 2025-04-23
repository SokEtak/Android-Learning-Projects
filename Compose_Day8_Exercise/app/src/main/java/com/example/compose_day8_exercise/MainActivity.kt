package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_day8_exercise.CounterViewModel
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set up the composable content to show the counter app
            CounterApp()
        }
    }
}

@Composable
fun CounterApp() {
    // Correctly initializing the CounterViewModel using viewModel() inside a composable function
    val counterViewModel: CounterViewModel = viewModel()

    // Access the current count value from the ViewModel
    val count = counterViewModel.count.value

    // Display the UI with a Column layout
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Show the current count
        Text(text = "Count: $count", fontSize = 32.sp)

        // Button to increment the count
        Button(onClick = {
            counterViewModel.increment()  // Increment count in ViewModel
        }) {
            Text("Increment")
        }

        // Button to decrement the count
        Button(onClick = {
            counterViewModel.decrement()  // Decrement count in ViewModel
        }) {
            Text("Decrement")
        }
    }
}
