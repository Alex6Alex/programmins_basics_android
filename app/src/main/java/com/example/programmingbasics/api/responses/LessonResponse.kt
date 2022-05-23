package com.example.programmingbasics.api.responses

import com.example.programmingbasics.api.data_objects.Lesson
import com.squareup.moshi.Json

data class LessonResponse(
  @Json(name = "data") val data: Lesson
)
