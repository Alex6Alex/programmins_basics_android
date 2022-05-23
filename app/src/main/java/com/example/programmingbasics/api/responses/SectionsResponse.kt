package com.example.programmingbasics.api.responses

import com.example.programmingbasics.api.data_objects.Section
import com.squareup.moshi.Json

data class SectionsResponse(
  @Json(name = "data") val data: List<Section>
)
