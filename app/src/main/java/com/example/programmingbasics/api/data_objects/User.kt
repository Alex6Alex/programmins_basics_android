package com.example.programmingbasics.api.data_objects

import com.squareup.moshi.Json

data class User(
  @Json(name = "id") val id: Long = 0,
  @Json(name = "email") val email: String,
  @Json(name = "password") val password: String? = null,
  @Json(name = "token") val token: String? = null
)
