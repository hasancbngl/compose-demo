package com.hasan.mvvmcompose_mealzapp.model.api

import com.hasan.mvvmcompose_mealzapp.model.response.MealsResponse
import com.hasan.mvvmcompose_mealzapp.util.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MealsApi {
    @GET("categories.php")
    suspend fun getMeals(): Response<MealsResponse>
}