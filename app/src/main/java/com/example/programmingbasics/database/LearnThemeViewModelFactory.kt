package com.example.programmingbasics.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class LearnThemeViewModelFactory(private val repository: LearnThemeRepository) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(LearnThemeViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return LearnThemeViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}
