package com.hasan.mvvmcompose_mealzapp.model

import com.hasan.mvvmcompose_mealzapp.model.api.RetrofitInstance
import com.hasan.mvvmcompose_mealzapp.model.response.MealResponse
import com.hasan.mvvmcompose_mealzapp.util.Resource

class MealsRepository {

    suspend fun getMeals(): Resource<List<MealResponse>> {
        return try {
            val response = RetrofitInstance.api.getMeals()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it.categories)
                } ?: Resource.Error("Error", null)
            } else {
                Resource.Error("Error", null)
            }
        } catch (e: Exception) {
            Resource.Error("${e.message} , ${e.printStackTrace()}", null)
        }
    }
}