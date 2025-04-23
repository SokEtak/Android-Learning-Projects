package com.example.navigationsample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationsample.ui.theme.NavigationSampleTheme

@Composable
fun FirstScreen(navigateToSecondScreen: (String,Int) -> Unit) {
    val inputName = remember { mutableStateOf("") }
    val inputAge = remember { mutableIntStateOf(0) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("First Screen", fontSize = 30.sp)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = inputName.value,
            onValueChange = { inputName.value = it },
            label = { Text("Enter Your Name") }
        )
        OutlinedTextField(
            value = inputAge.intValue.toString(),
            onValueChange = {  age ->
                               inputAge.intValue = age.toInt()  },
            label = { Text("Enter Age") }
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navigateToSecondScreen(inputName.value,inputAge.intValue) }) {
            Text("Go to Second Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    NavigationSampleTheme {
//        FirstScreen{}
    }
}
