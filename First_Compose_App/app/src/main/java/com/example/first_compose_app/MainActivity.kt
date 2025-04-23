package com.example.first_compose_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.first_compose_app.ui.theme.First_Compose_AppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            First_Compose_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("1") }
    var outputValue by remember { mutableStateOf("0.12") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableDoubleStateOf(0.01) }
    var oConversionFactor by remember { mutableDoubleStateOf(1.0) }

        val myStyle = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 32.sp,
            color = Color.Green
        )
        //function inside another function
        fun convertUnit(){
            //    ?: - elvis operator
            val inputValueAsDouble =  inputValue.toDoubleOrNull() ?: 0.0
            val result = (inputValueAsDouble * conversionFactor * 100 / oConversionFactor).roundToInt() / 100.0
            outputValue = "$result "
        }
    Column(//root
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text("Unit Converter", style = myStyle)
//        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange ={
                newValue-> inputValue = newValue
                convertUnit() },
            label = { Text("Enter any value")} //hint text
        )
            Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                //input box
                Button(onClick = { iExpanded = true},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Magenta, // Background color
                        contentColor = Color.White)// text color(white is default)
                )
                {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, //set button icon
                        contentDescription = null)
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text ={ Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit= "Centimeters"
                            conversionFactor = 0.01
                            convertUnit()
                        })
                    DropdownMenuItem(text ={ Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit= "Meters"
                            conversionFactor = 1.0
                            convertUnit()
                        })
                    DropdownMenuItem(text ={ Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit= "Feet"
                            conversionFactor = 0.3048
                            convertUnit()
                        })
                    DropdownMenuItem(text ={ Text("Mileometers")},
                        onClick = {
                            iExpanded = false
                            inputUnit= "Mileometers"
                            conversionFactor = 0.001
                            convertUnit()
                        })
                }
            }
                Spacer(modifier = Modifier.width(16.dp)) // add space between dropdown menu and dropdown menu
            Box {
                //output box
                Button(onClick = {oExpanded=true},
                    colors = ButtonDefaults.buttonColors(Color.Magenta)
                ){
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = null)
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(text ={ Text("Centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit= "Centimeters"
                            oConversionFactor = 0.01
                            convertUnit()
                        })
                    DropdownMenuItem(text ={ Text("Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit= "Meters"
                            oConversionFactor = 1.00
                            convertUnit()
                        })
                    DropdownMenuItem(text ={ Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit= "Feet"
                            oConversionFactor = 0.3048
                            convertUnit()
                        })
                    DropdownMenuItem(text ={ Text("Mileometers")},
                        onClick = {
                            oExpanded = false
                            outputUnit= "Mileometers"
                            oConversionFactor = 0.001
                            convertUnit()
                        })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        //Result
        Text("Result: $outputValue $outputUnit",
            style = myStyle)
    }
}

@Preview(showBackground=true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
