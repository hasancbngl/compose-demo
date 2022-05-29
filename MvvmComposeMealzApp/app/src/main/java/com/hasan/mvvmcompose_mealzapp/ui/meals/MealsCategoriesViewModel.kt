package com.hasan.mvvmcompose_mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasan.mvvmcompose_mealzapp.model.MealsRepository
import com.hasan.mvvmcompose_mealzapp.model.response.MealResponse
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
) : ViewModel() {

    suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }
}