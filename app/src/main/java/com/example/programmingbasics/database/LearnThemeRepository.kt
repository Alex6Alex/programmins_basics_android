package com.example.programmingbasics.database

import kotlinx.coroutines.flow.Flow

class LearnThemeRepository(private val learnThemeDao: LearnThemeDao) {
  val allThemesWithLessons: Flow<List<LearnThemeWithLessons>> = learnThemeDao.getAllWithLessons()

  suspend fun insert(learnTheme: LearnTheme) {
    learnThemeDao.insert(learnTheme)
  }
}