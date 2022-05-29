package com.hasan.mvvmcompose_mealzapp.model

import com.hasan.mvvmcompose_mealzapp.model.api.MealsWebService
import com.hasan.mvvmcompose_mealzapp.model.response.MealsCategories

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealsCategories = webService.getMeals()
}