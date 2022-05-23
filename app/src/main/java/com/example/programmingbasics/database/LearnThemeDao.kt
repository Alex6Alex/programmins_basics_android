package com.example.programmingbasics.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LearnThemeDao {
  @Query("SELECT * FROM learn_themes")
  fun getAll(): Flow<List<LearnTheme>>

  @Transaction
  @Query("SELECT * FROM learn_themes")
  fun getAllWithLessons(): Flow<List<LearnThemeWithLessons>>

  @Query("SELECT * FROM learn_themes ORDER BY id DESC LIMIT 1")
  fun getLast(): Flow<LearnTheme>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(learnTheme: LearnTheme)

  @Query("DELETE FROM learn_themes")
  suspend fun deleteAll()
}
