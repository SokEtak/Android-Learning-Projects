package com.example.navigationsample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationsample.ui.theme.NavigationSampleTheme

@Composable
fun SecondScreen(name: String, age:Int, navigateToThirdScreen: (String,Int) -> Unit) {
    var inputName by remember { mutableStateOf("") }
    var inputAge by remember { mutableIntStateOf(0) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Second Screen", fontSize = 30.sp)
        Text("Hello $name,You are $age years old.", fontSize = 20.sp)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = inputName,
            onValueChange = { inputName = it },
            label = { Text("Enter Age Name") }
        )
        OutlinedTextField(
            value = inputAge.toString(),
            onValueChange = { inputAge = it.toInt() },
            label = {Text("Enter Age")}
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navigateToThirdScreen(inputName,inputAge) }) {
            Text("Go to Third Screen")
        }
    }
}

