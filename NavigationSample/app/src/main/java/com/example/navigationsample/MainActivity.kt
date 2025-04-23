package com.example.navigationsample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSampleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ScreenApp()
                }
            }
        }
    }
}

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun ScreenApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first_screen") {

        // Screen 1
        composable("first_screen") {
            FirstScreen { newName , age->
                navController.navigate("second_screen/$newName/$age")
            }
        }

        // Screen 2
        composable("second_screen/{name}/{age}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "No Name"
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0 // Convert to Int
            SecondScreen(name,age) { updatedName , newAge->
                navController.navigate("third_screen/$updatedName/$age") // Pass name to Screen 3
            }
        }

        // Screen 3
        composable("third_screen/{name}/{age}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "No Name"
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0 // Convert to Int
            ThirdScreen(name,age) {
                navController.navigate("first_screen") // Go back to Screen 1
            }
        }
    }
}

