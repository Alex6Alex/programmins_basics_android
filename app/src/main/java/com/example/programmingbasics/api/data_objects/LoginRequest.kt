package com.example.programmingbasics.api.data_objects

import com.squareup.moshi.Json

data class LoginRequest(
  @Json(name = "email") val email: String,
  @Json(name = "password") val password: String
)
