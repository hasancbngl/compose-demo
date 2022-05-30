package com.hasan.mvvmcompose_mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasan.mvvmcompose_mealzapp.model.MealsRepository
import com.hasan.mvvmcompose_mealzapp.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository.getInstance()
) : ViewModel() {

    val meals: MutableState<List<MealResponse>> = mutableStateOf(emptyList())

    init {
        getMeals()
    }

    private fun getMeals() = viewModelScope.launch(Dispatchers.IO) {
        meals.value = repository.getMeals().categories
    }

}