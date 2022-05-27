package com.hasan.mvvmcompose_mealzapp.ui.meals

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Composable
fun MealsCategoriesScreen() {
    val viewModel : MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealsResponse
    Log.e("TAG", "MealsCategoriesScreen: $meals", )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MvvmComposeMealzAppTheme {
        MealsCategoriesScreen()
    }
}