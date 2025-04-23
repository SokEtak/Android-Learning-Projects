package com.example.textcustomizetext

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textcustomizetext.ui.theme.TextCustomizeTextTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextCustomizeTextTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                Spacer(Modifier.height(100.dp))
                    Column {
                        Text(
                            "Hello",
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(10.dp),
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun CustomText(text : String){
    Column {
        Text(
            text = text,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(10.dp)
        )

        //StringResource()
        val txtFromResource = stringResource(id = R.string.app_name)
        Text(text = txtFromResource,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(10.dp))
    }
}

@Composable
fun CustomText2(){
    Column {
        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(textAlign = TextAlign.Left,))
                {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 100.sp
                        )
                    )
                    { append("A") }

                    append("B")
                    append("C")
                }
            },
            modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.DarkGray)
        )
    }
}

@Composable
fun CustomText3(){
    //TextOverflow.Clip: The overflowing text is simply cut off without adding any visual indicator.
    //TextOverflow.Visible: No truncation; the text overflows its boundaries, which might cause it to overlap other UI elements.
    //TextOverflow.Ellipsis:
    //
    //This option truncates the text that doesn't fit within the maxLines limit and appends an ellipsis (...) at the end of the visible portion of the text.
    //It visually indicates that the text is too long and some content is not displayed.

    Text(text = "Hello".repeat(20), maxLines = 1, overflow = TextOverflow.Ellipsis)
    Text(text = "Good".repeat(20), maxLines = 1, overflow = TextOverflow.Clip)
    Text(text = "Hypertext".repeat(20), maxLines = 1, overflow = TextOverflow.Visible)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextCustomizeTextTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            //CustomText("Hello World")
            CustomText3()
        }

    }
}