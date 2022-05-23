package com.example.programmingbasics.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
  @Query("SELECT * FROM lessons")
  fun getAll(): Flow<List<Lesson>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(lesson: Lesson)

  @Query("DELETE FROM lessons")
  suspend fun deleteAll()
}
