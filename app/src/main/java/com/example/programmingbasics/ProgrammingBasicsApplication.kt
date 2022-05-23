package com.example.programmingbasics

import android.app.Application
import com.example.programmingbasics.database.LearnThemeRepository
import com.example.programmingbasics.database.ProgrammingBasicsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ProgrammingBasicsApplication : Application() {
  private val applicationScope = CoroutineScope(SupervisorJob())

  private val database by lazy { ProgrammingBasicsDatabase.getInstance(this, applicationScope) }
  val repository by lazy { LearnThemeRepository(database.learnThemeDao()) }
}