package com.example.programmingbasics.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Database(version = 1, entities = [LearnTheme::class, Lesson::class], exportSchema = false)
abstract class ProgrammingBasicsDatabase : RoomDatabase() {
  abstract fun learnThemeDao(): LearnThemeDao
  abstract fun lessonDao(): LessonDao

  companion object {
    @Volatile
    private var INSTANCE: ProgrammingBasicsDatabase? = null

    fun getInstance(context: Context, scope: CoroutineScope): ProgrammingBasicsDatabase {
      return INSTANCE ?: synchronized(this) {
//        context.deleteDatabase("programming_basics_database")
        val instance = Room.databaseBuilder(
          context.applicationContext,
          ProgrammingBasicsDatabase::class.java,
          "programming_basics_database"
        ).fallbackToDestructiveMigration().addCallback(PopulateDatabaseCallback(scope)).build()

        INSTANCE = instance
        instance
      }
    }

    private class PopulateDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
      override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
          scope.launch {
            val learnThemeDao = database.learnThemeDao()
            val lessonDao = database.lessonDao()

            learnThemeDao.insert(LearnTheme(name = "Вступление", number = 1, description = "Начало изучения алгоритмов"))
            learnThemeDao.insert(LearnTheme(name = "Раздел 1", number = 2, description = "Описание раздела 1"))
            learnThemeDao.insert(LearnTheme(name = "Раздел 2", number = 3, description = "Описание раздела 2"))

            learnThemeDao.getAll().collect { list ->
              list.forEach { item ->
                lessonDao.insert(Lesson(name = "Приветсвие", learnThemeId = item.id, description = "Знакомство с кусром", number = 1))
                lessonDao.insert(Lesson(name = "Второй урок", learnThemeId = item.id, description = "Изучение нового", number = 2))
                lessonDao.insert(Lesson(name = "Третий урок", learnThemeId = item.id, description = "Продолжение изучения", number = 3))
              }
            }
          }
        }
      }
    }
  }
}