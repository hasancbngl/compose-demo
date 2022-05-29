package com.hasan.mvvmcompose_mealzapp.ui

import MealsCategoriesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hasan.mvvmcompose_mealzapp.ui.theme.MvvmComposeMealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmComposeMealzAppTheme {
                MealsCategoriesScreen()
            }
        }
    }
}