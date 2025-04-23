package com.example.subscriptandsuperscript

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subscriptandsuperscript.ui.theme.SubScriptAndSuperScriptTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubScriptAndSuperScriptTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                }
            }
        }
    }
}

@Composable
fun SuperScriptText() {
    Text(
        buildAnnotatedString {
            append("10")

            withStyle(
                style = SpanStyle(
                    fontSize = 15.sp,
                    color = Color.Red,
                    baselineShift = BaselineShift.Superscript
                )
            ) {
                append("3 ")
            }
            append("(Superscript)")
        }
    )
}


@Composable
fun SubScriptText(){
    Text(
        buildAnnotatedString {
            append("H")

            withStyle(
                style = SpanStyle(
                    fontSize = 15.sp,
                    color = Color.Red,
                    baselineShift = BaselineShift.Subscript
                )
            ) {
                append("2")
            }
                append("O(Subscript)")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SubScriptAndSuperScriptTheme {

        Column(Modifier.fillMaxSize()) {

            SuperScriptText()
            Spacer(Modifier.height(20.dp))
            SubScriptText()
        }

    }
}