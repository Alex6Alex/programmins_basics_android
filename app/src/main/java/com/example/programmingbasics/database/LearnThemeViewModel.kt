package com.example.programmingbasics.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LearnThemeViewModel(private val repository: LearnThemeRepository) : ViewModel() {
  val allThemesWithLessons: LiveData<List<LearnThemeWithLessons>> = repository.allThemesWithLessons.asLiveData()

  fun insert(learnTheme: LearnTheme) = viewModelScope.launch {
    repository.insert(learnTheme)
  }
}
