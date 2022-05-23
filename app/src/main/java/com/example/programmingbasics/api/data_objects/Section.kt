package com.example.programmingbasics.api.data_objects

import com.squareup.moshi.Json

data class Section(
  @Json(name = "id") val id: Long,
  @Json(name = "name") val name: String,
  @Json(name = "description") val description: String,
  @Json(name = "order") val order: Int,
  @Json(name = "lessons") val lessons: List<Lesson>
)
