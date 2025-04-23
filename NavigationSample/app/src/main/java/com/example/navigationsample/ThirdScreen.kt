package com.example.navigationsample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThirdScreen(name: String , age: Int, navigateToFirstScreen: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Third Screen", fontSize = 30.sp)
        Spacer(Modifier.height(20.dp))
        Text("Hello, $name ,you're $age years old.", fontSize = 20.sp)
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navigateToFirstScreen() }) {
            Text("Go to First Screen")
        }
    }
}

