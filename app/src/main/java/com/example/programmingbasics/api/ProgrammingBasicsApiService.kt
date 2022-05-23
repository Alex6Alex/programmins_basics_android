package com.example.programmingbasics.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = Constants.BASE_URL

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
  .addConverterFactory(MoshiConverterFactory.create(moshi))
  .baseUrl(BASE_URL)
  .build()

object ProgrammingBasicsApi {
  val retrofitService: ProgrammingBasicsApiService by lazy {
    retrofit.create(ProgrammingBasicsApiService::class.java)
  }
}
