package com.example.programmingbasics.api.data_objects

import com.squareup.moshi.Json

data class LoginRequest(
  @Json(name = "user") val user: User
)
