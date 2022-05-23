package com.example.programmingbasics

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> ComponentActivity.viewModelsFactory(crossinline viewModelInstance: () -> T): Lazy<T> {
  return viewModels {
    object : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelInstance.invoke() as T
      }
    }
  }
}
