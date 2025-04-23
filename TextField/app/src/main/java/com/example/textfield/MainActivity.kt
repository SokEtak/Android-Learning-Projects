package com.example.textfield

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.textfield.ui.theme.TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldTheme {
                Surface {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    // State variables for different TextFields
    var username by remember { mutableStateOf("") }
    var disabledText by remember { mutableStateOf("This is disabled") }
    var readOnlyText by remember { mutableStateOf("This is read-only") }
    var labelText by remember { mutableStateOf("") }
    var singleLineText by remember { mutableStateOf("") }
    var multiLineText by remember { mutableStateOf("") }
    var limitedLineText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Editable TextField
//        TextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Editable Text Field") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Disabled TextField
//        TextField(
//            value = disabledText,
//            onValueChange = {},
//            enabled = false,
//            label = { Text("Disabled Text Field") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Read-only TextField
//        TextField(
//            value = readOnlyText,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Read-only Text Field") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // TextField with Label
//        TextField(
//            value = labelText,
//            onValueChange = { labelText = it },
//            label = { Text("Label with Text Field") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Single Line TextField
//        TextField(
//            value = singleLineText,
//            onValueChange = { singleLineText = it },
//            singleLine = true,
//            label = { Text("Single Line Text Field") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Multi-line TextField
//        TextField(
//            value = multiLineText,
//            onValueChange = { multiLineText = it },
//            label = { Text("Multi-line Text Field") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Limited Line Text Field
//        TextField(
//            value = limitedLineText,
//            onValueChange = { limitedLineText = it },
//            label = { Text("Limited Line Text Field") },
//            maxLines = 2,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Text Field with start (leading) icon
//        TextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Text Field with Start Icon") },
//            leadingIcon = {
//                IconButton(onClick = { Log.d("Icons", "Email Icon Clicked") }) {
//                    Icon(Icons.Filled.Email, contentDescription = "Email Icon")
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Text Field with start and end icons
//        TextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Text Field with Start and End Icons") },
//            leadingIcon = {
//                IconButton(onClick = { Log.d("Icons", "Email Icon Clicked") }) {
//                    Icon(Icons.Filled.Email, contentDescription = "Email Icon")
//                }
//            },
//            trailingIcon = {
//                IconButton(onClick = { Log.d("Icons", "CheckCircle Icon Clicked") }) {
//                    Icon(Icons.Filled.CheckCircle, contentDescription = "CheckCircle Icon")
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        )

        // Keyboard Options and action
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Editable Text Field with Keyboard Option") }, // label inside the text field
            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,//display type of keyboard
                                imeAction = ImeAction.Search),//the icon inside the keyboard
                                             //ime action and keyboard action must matched to make log.d is printed
            keyboardActions = KeyboardActions(onSearch = {Log.d("ImeIcon","Clicked")}), //action occur when ime icon got click
            modifier = Modifier.fillMaxWidth()

        )
        var str by remember { mutableStateOf("") }
        //Outline Text Field
        OutlinedTextField(modifer = value = str, onValueChange = {newValue -> str = newValue }, label = {Text("Outline Text Field")})
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextFieldTheme {
        Greeting()
    }
}
