package com.example.programmingbasics.ui.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingbasics.api.ProgrammingBasicsApi
import com.example.programmingbasics.api.data_objects.Section
import kotlinx.coroutines.launch
import java.lang.Exception

class SectionViewModel(private val token: String?) : ViewModel() {
  private val _statusMsg = MutableLiveData<String>()
  private val _sections = MutableLiveData<List<Section>>()

  val statusMessage: LiveData<String> = _statusMsg
  val records: LiveData<List<Section>> = _sections

  init {
    getSections()
  }

  private fun getSections() {
    viewModelScope.launch {
      try {
        val response = ProgrammingBasicsApi.client.getSections("Bearer $token")
        _statusMsg.value = "Success: ${response.data.size} sections were loaded"
        _sections.value = response.data
      } catch (e: Exception) {
        _statusMsg.value = "Failure: ${e.message}"
      }
    }
  }
}