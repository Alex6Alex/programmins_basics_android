package com.example.programmingbasics.api.responses

import com.example.programmingbasics.api.data_objects.User
import com.squareup.moshi.Json

data class UserResponse(
  @Json(name = "data") val data: User
)
