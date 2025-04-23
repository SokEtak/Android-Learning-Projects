package com.example.compose_capitaingame

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_capitaingame.ui.theme.Compose_CapitainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_CapitainGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CapitanGame()
                }
            }
        }
    }


@Composable
fun CapitanGame() {
    //u can print it without using .value
    //val direction by remember { mutableStateOf("Sail East")}
    val direction = remember { mutableStateOf("Sail East") }
    val treasureFound = remember { mutableIntStateOf(0) }
    val stormTreasure = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "We Found The Treasure!!!!: ${treasureFound.intValue}")
//        Text(text = "Current Direction: $direction") using by keyword
        Text(text = "Current Direction: ${direction.value}") //not using by keyword
        Text(text = stormTreasure.value)
        Box() {
            Row() {
                // Apply Modifier.weight() to make all buttons take equal space
                Button(
                    onClick = {
                        direction.value = "Sail East"
                        if (Random.nextBoolean()) {
                            treasureFound.intValue += 1
                            stormTreasure.value ="We Found The Treasure!!!!"
                        } else{
                            stormTreasure.value="Storm Ahead!!!!!!!"
                        }
                    },
                    modifier = Modifier.weight(2f) // Ensures equal size for all buttons
                ) {
                    Text("Sail East")
                }

                Button(
                    onClick = {
                        if (Random.nextBoolean()) {
                            treasureFound.intValue += 1
                            stormTreasure.value ="We Found The Treasure!!!!"
                        }else{
                            stormTreasure.value="Storm Ahead!!!!!!!"
                        }
                    },
                    modifier = Modifier.weight(2f) // Ensures equal size for all buttons
                ) {
                    Text("Sail West")
                }

                Button(
                    onClick = {
                        direction.value = "Sail North"
                        if (Random.nextBoolean()) {
                            treasureFound.intValue += 1
                            stormTreasure.value ="We Found The Treasure!!!!"
                        } else{
                            stormTreasure.value="Storm Ahead!!!!!!!"
                        }
                    },
                    modifier = Modifier.weight(2f) // Ensures equal size for all buttons
                ) {
                    Text("Sail North")
                }

                Button(
                    onClick = {
                        direction.value = "Sail South"
                        if (Random.nextBoolean()) {
                            treasureFound.intValue += 1
                            stormTreasure.value ="We Found The Treasure!!!!"
                        } else{
                            stormTreasure.value="Storm Ahead!!!!!!!"
                        }
                    },
                    modifier = Modifier.weight(2f) // Ensures equal size for all buttons
                ) {
                    Text("Sail South")
                }
            }
        }
    }
}

@Preview(showBackground=true)
@Composable
fun CapitanGamePreview() {
    CapitanGame()
}
}