//layouts
package com.example.column_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.column_compose.ui.theme.Column_ComposeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column_ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ColumnPreview()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ColumnPreview() {
    Column_ComposeTheme {

        Box(modifier = Modifier.fillMaxSize()) {
//            Row(modifier = Modifier.height(300.dp),
//                horizontalArrangement = Arrangement.SpaceBetween){

                Box(Modifier.height(100.dp).width(100.dp).background(Color.DarkGray)) {
                    Text(
                        text = "Box A",
                        fontSize = 30.sp,
                        modifier = Modifier.verticalScroll(rememberScrollState())// allow vertically scrollable
                    )
                }

//                Spacer(Modifier.width(10.dp))

                Box(Modifier.height(100.dp).width(100.dp).background(Color.DarkGray)) {
                    Text(
                        text = "Box B",
                        fontSize = 30.sp,
                        modifier = Modifier.verticalScroll(rememberScrollState())// allow vertically scrollable
                    )
                }
                Box(Modifier.height(100.dp).width(100.dp).background(Color.DarkGray)) {
                    Text(
                        text = "Box C",
                        fontSize = 30.sp,
                        modifier = Modifier.verticalScroll(rememberScrollState())// allow vertically scrollable
                    )
                }
                Box(Modifier.height(100.dp).width(100.dp).background(Color.DarkGray)) {
                    Text(
                        text = "Box D",
                        fontSize = 30.sp,
                        modifier = Modifier.verticalScroll(rememberScrollState())// allow vertically scrollable
                    )
                }
//            } //
        }
    }
}