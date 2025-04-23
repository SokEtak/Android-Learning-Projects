package com.example.singupgooglebutton

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun GoogleButton() {
    var isClicked by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }

    // Dynamically update the text based on `isClicked`
    val buttonText = when {
        isSuccess -> "Account Successfully Created"
        isClicked -> "Creating Google Account"
        else -> "Sign Up With Google"
    }

    // Handle the timer for showing the success message after 5 second
    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(3000)  // 5 second
            isSuccess = true  // After 5 second, change the text to "Account Successfully Created"
        }
    }

    Surface(
        onClick = { isClicked = !isClicked },
        modifier = Modifier
            .height(100.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = EaseInOutBack
                )
            ),
        shape = RoundedCornerShape(60.dp),
        color = Color.LightGray,
        //shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = "Google Icon",
                modifier = Modifier.size(50.dp),
                tint = Color.Unspecified
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = buttonText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.width(10.dp))

            if (isClicked && !isSuccess) {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    color = Color.Green
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleButtonPreview() {
    GoogleButton()
}
