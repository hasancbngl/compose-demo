package com.hasan.simpletextcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingButton()
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.background(shape = RectangleShape, color = Color.White),
        color = Color.Red,
        textAlign = TextAlign.Center,
        fontSize = 18.sp
    )
}

@Composable
fun GreetingButton() {
    Button(onClick = {
        Log.e("TAG", "GreetingButton: ", )
        //Toast.makeText(, "hey there", Toast.LENGTH_SHORT).show()
    },
    shape = CircleShape) {
        GreetingText(name = "button")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreetingButton()
}