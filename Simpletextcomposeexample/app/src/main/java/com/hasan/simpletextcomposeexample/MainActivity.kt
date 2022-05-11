package com.hasan.simpletextcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingText(name = "World!")
        }
    }
}

@Composable
fun GreetingText(name: String) {
    //fillMaxWidth / fillMaxHeight can use percentages
    //size(width, height) shorter usage of .width and .height
    //.clickable modifier adds onClick
    //compose modifiers order matters

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .background(color = Color.Yellow)
            .border(2.dp, shape = RectangleShape, color = Color.Black)
            .padding(start = 16.dp)
            .clickable { Log.e("TAG", "GreetingText: ") },
        color = Color.Red,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun GreetingButton() {
    Button(
        onClick = {
            Log.e("TAG", "GreetingButton: ")
            //Toast.makeText(, "hey there", Toast.LENGTH_SHORT).show()
        },
        shape = CircleShape
    ) {
        GreetingText(name = "button")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreetingText(name = "World!")
}