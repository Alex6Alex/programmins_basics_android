package com.example.programmingbasics.database

import androidx.room.*

@Entity(tableName = "learn_themes")
data class LearnTheme(
  @PrimaryKey(autoGenerate = true) var id: Long = 0,
  @ColumnInfo(name = "name") var name: String,
  @ColumnInfo(name = "number") var number: Int,
  @ColumnInfo(name = "description") var description: String
)

data class LearnThemeWithLessons(
  @Embedded var learnTheme: LearnTheme,
  @Relation(parentColumn = "id", entityColumn = "learn_theme_id") var lessons: List<Lesson>
)
