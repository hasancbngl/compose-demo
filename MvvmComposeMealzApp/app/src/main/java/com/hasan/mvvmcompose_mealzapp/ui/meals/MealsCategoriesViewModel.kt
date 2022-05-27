package com.hasan.mvvmcompose_mealzapp.ui.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasan.mvvmcompose_mealzapp.model.MealsRepository
import com.hasan.mvvmcompose_mealzapp.model.response.MealResponse
import com.hasan.mvvmcompose_mealzapp.util.Resource
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.io.IOException

class MealsCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
) : ViewModel() {

    private val _mealsData : MutableLiveData<Resource<List<MealResponse>>> = MutableLiveData()
    val mealsResponse = _mealsData

    init {
        getMeals()
    }

    fun getMeals()  = viewModelScope.launch {
            safeRequestMealsData()
        }


    private suspend fun safeRequestMealsData() {
        _mealsData.postValue(Resource.Loading())
        try {
            val response = repository.getMeals()
            _mealsData.postValue(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _mealsData.postValue(Resource.Error("Network Failure"))
                else -> _mealsData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
}