package com.example.imageloadinglibrarywithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.example.imageloadinglibrarywithjetpackcompose.ui.theme.ImageLoadingLibraryWithJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageLoadingLibraryWithJetpackComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CoilImage()
                }
            }
        }
    }
}

@Composable
fun CoilImage() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart)
    {
        val painter  = rememberA  // rememberImagePainter()
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageLoadingLibraryWithJetpackComposeTheme {
        CoilImage()
    }
}