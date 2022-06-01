package com.hasan.cleanart_noteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hasan.cleanart_noteapp.ui.theme.CleanArt_NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArt_NoteAppTheme {

            }
        }
    }
}