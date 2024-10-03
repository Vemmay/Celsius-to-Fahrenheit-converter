package com.example.celciustofahrenheit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.celciustofahrenheit.ui.theme.CelciusToFahrenheitTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CelciusToFahrenheitTheme {
                TemperatureConverter()
            }
        }
    }
}
@Composable
fun TemperatureConverter() {
    var celsius by remember { mutableFloatStateOf(0f) }
    var fahrenheit by remember { mutableFloatStateOf(32f) }

    // Convert Celsius to Fahrenheit
    fun celsiusToFahrenheit(celsius: Float): Float {
        return (celsius * 9/5) + 32
    }

    // Convert Fahrenheit to Celsius
    fun fahrenheitToCelsius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5/9
    }

    // Update Celsius slider and automatically set Fahrenheit (ChatGPT)
    fun updateCelsius(newCelsius: Float) {
        celsius = newCelsius
        fahrenheit = celsiusToFahrenheit(newCelsius)
    }

    // Update Fahrenheit slider and automatically set Celsius (ChatGPT)
    fun updateFahrenheit(newFahrenheit: Float) {
        if (newFahrenheit < 32) {
            fahrenheit = 32f
            celsius = 0f
        } else {
            fahrenheit = newFahrenheit
            celsius = fahrenheitToCelsius(newFahrenheit)
        }
    }

    // Determine message based on Celsius temperature
    val message = if (celsius <= 20) "I wish it were warmer." else "I wish it were colder."

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Celsius: ${round(celsius)}ºC")
        Slider(
            value = celsius,
            onValueChange = { updateCelsius(it) },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Fahrenheit: ${round(fahrenheit)}ºF")
        Slider(
            value = fahrenheit,
            onValueChange = { updateFahrenheit(it) },
            valueRange = 0f..212f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun ConverterScreenPreview() {
}

