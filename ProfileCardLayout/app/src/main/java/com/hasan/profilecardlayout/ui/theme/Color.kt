package com.hasan.profilecardlayout.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val LightGray = Color(0xbbbbbbbb)
var LightGreen200 = Color(0xDD66F013)
var black00 = Color(0xFF000000)


//write extension fun for colors of mateirls theme
val ColorScheme.lightGreen: Color
    @Composable
    get() = LightGreen200

val ColorScheme.black: Color
    @Composable
    @ReadOnlyComposable
    get() = black00