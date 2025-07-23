package com.example.coutermvvm   // (typo kept if this is your package)

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coutermvvm.ui.theme.CouterMVVMTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CounterViewModel = viewModel()
            CouterMVVMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CounterApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun CounterApp(viewModel: CounterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Count: ${viewModel.count.value}",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { viewModel.increment() },
                enabled = true                    // always enabled
            ) {
                Text("Increment")
            }
            Button(
                onClick = { viewModel.decrement() },
                enabled = viewModel.count.value > 0   // disabled when 0
            ) {
                Text("Decrement")
            }
        }
    }
}

/* ---------- Preview ---------- */

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    // Use a *plain instance* (or a fake) because ViewModelStoreOwner
    // isn't present in design / preview mode.
    val previewVm = CounterViewModel()

    CouterMVVMTheme {
        CounterApp(viewModel = previewVm)
    }
}
