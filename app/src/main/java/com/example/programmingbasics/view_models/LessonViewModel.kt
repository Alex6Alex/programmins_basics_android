package com.example.programmingbasics.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingbasics.api.ProgrammingBasicsApi
import com.example.programmingbasics.api.data_objects.Lesson
import kotlinx.coroutines.launch
import java.lang.Exception

class LessonViewModel(private val lessonId: Int) : ViewModel() {
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
        val response = ProgrammingBasicsApi.retrofitService.getLesson(lessonId, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjU4MzI1NjE1fQ.M-WabFpU2_EzAnmUuFQJ5s2SaHX95SZrPf4Lo4ANL40")
        _statusMsg.value = "Success: lesson were loaded"
        _record.value = response.data
      } catch (e: Exception) {
        _statusMsg.value = "Failure: ${e.message}"
      }
    }
  }
}