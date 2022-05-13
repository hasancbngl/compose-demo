package com.hasan.dynamiccontentexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    val greetingsListState = remember {
        mutableStateListOf("John", "Elisa", "Ed", "Lena")
    }
    val newNameState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 24.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        GreetingList(greetingsListState,
            {
                newNameState.value.let {
                    if (it.isNotEmpty()) {
                        greetingsListState.add(it)
                        newNameState.value = ""
                    }
                }
            }, newNameState.value, { newNameState.value = it })

        RemoveItemFromList {
            if (greetingsListState.size > 0) greetingsListState.removeAt(0)
        }
    }
}

@Composable
fun GreetingList(
    names: List<String>,
    addName: () -> Unit,
    textFieldValue: String,
    textFieldUpdate: (newName: String) -> Unit
) {
    names.forEach {
        Greeting(name = it)
    }
    TextField(
        value = textFieldValue,
        onValueChange = textFieldUpdate,
        modifier = Modifier.padding(0.dp, 24.dp, 0.dp, 0.dp),
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Red)
    )

    Button(
        modifier = Modifier
            .padding(0.dp, 30.dp, 0.dp, 0.dp),
        onClick = addName,
        shape = CircleShape,
        border = BorderStroke(3.dp, Color.Gray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue, contentColor = Color.White
        )
    ) {
        Text(
            text = "Add new Name",
            fontSize = 18.sp
        )
    }
}

@Composable
fun RemoveItemFromList(removeName: () -> Unit) {
    Button(
        onClick = removeName,
        modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp),
        shape = RectangleShape,
        border = BorderStroke(2.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, contentColor = Color.Black
        )
    ) {
        Text(
            text = "Remove first Name",
            fontSize = 18.sp
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = TextStyle(Color.Blue, fontSize = 20.sp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}