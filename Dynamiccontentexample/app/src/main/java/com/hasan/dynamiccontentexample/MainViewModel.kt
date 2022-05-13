package com.hasan.dynamiccontentexample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val textFieldState = MutableLiveData("")

    fun onTextChanged(newText: String){
        textFieldState.postValue(newText)
    }
}