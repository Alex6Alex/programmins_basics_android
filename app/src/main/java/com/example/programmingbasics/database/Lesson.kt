package com.example.programmingbasics.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class Lesson(
  @PrimaryKey(autoGenerate = true) var id: Long = 0,
  @ColumnInfo(name = "name") var name: String,
  @ColumnInfo(name = "learn_theme_id") var learnThemeId: Long,
  @ColumnInfo(name = "description") var description: String,
  @ColumnInfo(name = "number") var number: Int
)
