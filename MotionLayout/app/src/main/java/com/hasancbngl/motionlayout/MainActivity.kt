package com.hasancbngl.motionlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hasancbngl.motionlayout.ui.theme.MotionLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutTheme {
                // A surface container using the 'background' color from the theme
                MotionLayoutWithSliderExample()
            }
        }
    }
}