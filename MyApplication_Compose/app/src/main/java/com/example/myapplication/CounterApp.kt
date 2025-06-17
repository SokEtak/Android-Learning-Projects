package com.example.myapplication

// CounterApp.kt
import CounterViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CounterApp(counterViewModel: CounterViewModel) {
    // Display the current counter value
    Text(text = "Counter: ${counterViewModel.count.value}")

    // Button to increment the counter
    Button(onClick = { counterViewModel.increment() }) {
        Text("Increment")
    }

    // Button to decrement the counter
    Button(onClick = { counterViewModel.decrement() }) {
        Text("Decrement")
    }
}
