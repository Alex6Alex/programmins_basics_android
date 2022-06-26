package com.example.programmingbasics.ui.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingbasics.api.ProgrammingBasicsApi
import com.example.programmingbasics.api.data_objects.Lesson
import com.example.programmingbasics.api.data_objects.LessonUnit
import kotlinx.coroutines.launch
import java.lang.Exception

class LessonViewModel(private val lessonId: Int, private val token: String?) : ViewModel() {
  private val _statusMsg = MutableLiveData<String>()
  private val _record = MutableLiveData<Lesson>()

  val statusMsg: LiveData<String> = _statusMsg
  val record: LiveData<Lesson> = _record

  init {
    getLesson()
  }

  private fun getLesson() {
    viewModelScope.launch {
      try {
        val response = ProgrammingBasicsApi.client.getLesson(lessonId, "Bearer $token")
        _statusMsg.value = "Success: lesson were loaded"
        _record.value = response.data
      } catch (e: Exception) {
        _statusMsg.value = "Failure: ${e.message}"
      }
    }
  }

  fun markUnitAsRead(lessonUnit: LessonUnit) {
    viewModelScope.launch {
      try {
        val response = ProgrammingBasicsApi.client.passLessonUnit(lessonUnit.id, "Bearer $token")
        _statusMsg.value = "Success: lesson unit was passed"
      } catch (e: Exception) {
        _statusMsg.value = "Failure: ${e.message}"
      }
    }
  }
}