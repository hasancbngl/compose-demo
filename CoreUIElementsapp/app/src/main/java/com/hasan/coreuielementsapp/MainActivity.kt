package com.hasan.coreuielementsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasan.coreuielementsapp.ui.theme.CoreUIElementsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        color = Color.Blue,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.background(Color.Gray)
        ) {
            val modifierTop = Modifier
                .width(60.dp)
                .height(600.dp)
                .padding(8.dp)
                .align(Alignment.Top)
            val modifierBottom = Modifier
                .width(60.dp)
                .height(600.dp)
                .padding(8.dp)
                .align(Alignment.Bottom)

            HorizantalBar(color = Color.Yellow, modifierTop)
            HorizantalBar(color = Color.Green, modifierBottom)
            HorizantalBar(color = Color.Black, modifierTop)
            HorizantalBar(color = Color.Blue, modifierBottom)
            HorizantalBar(color = Color.Red, modifierTop)
            HorizantalBar(color = Color.Cyan, modifierBottom)
        }
    }
}

@Composable
fun HorizantalBar(color: Color, modifier: Modifier) {
    Surface(
        color = color,
        modifier = modifier
    ) {
        Text(
            text = "Test", color = Color.White,
            modifier = Modifier.wrapContentSize(),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}