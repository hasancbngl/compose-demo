package com.hasan.mvvmcompose_mealzapp.model

import com.hasan.mvvmcompose_mealzapp.model.api.MealsWebService
import com.hasan.mvvmcompose_mealzapp.model.response.MealResponse
import com.hasan.mvvmcompose_mealzapp.model.response.MealsCategories

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var meals = listOf<MealResponse>()

    suspend fun getMeals(): MealsCategories {
        val response = webService.getMeals()
        meals = response.categories
        return response
    }

    fun getMeal(id: String): MealResponse? = meals.firstOrNull { it.id == id }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also {
                instance = it
            }
        }
    }
}