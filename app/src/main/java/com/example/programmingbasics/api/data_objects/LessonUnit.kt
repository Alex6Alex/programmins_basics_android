package com.example.programmingbasics.api.data_objects

import com.squareup.moshi.Json

data class LessonUnit(
  @Json(name = "id") val id: Long,
  @Json(name = "content_block") val contentBlock: String,
  @Json(name = "order") val order: Int,
  @Json(name = "is_passed") val isPassed: Boolean
)
