package com.example.programmingbasics.api.data_objects

import com.squareup.moshi.Json

data class User(
  val id: Int,
  @Json(name = "email") val email: String,
  @Json(name = "token") val token: String
)
