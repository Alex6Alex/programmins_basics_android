package com.example.programmingbasics.api.data_objects

import com.squareup.moshi.Json

data class LessonUnit(
  @Json(name = "content_block") val contentBlock: String,
  @Json(name = "order") val order: Int
)
