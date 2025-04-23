package com.example.selectabletext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.selectabletext.ui.theme.SelectableTextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SelectableTextTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CustomText()
                }
            }
        }
    }
}

@Composable
fun CustomText(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        SelectionContainer {
        Text(
            text = "Selectable Text",
            modifier = modifier
        )
    }
        DisableSelection {
            Text(
                text = "Disable Selection Text",
                modifier = modifier
            )
        } }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SelectableTextTheme {
        Column(modifier = Modifier.fillMaxSize()) {
                CustomText()
        }

    }
}