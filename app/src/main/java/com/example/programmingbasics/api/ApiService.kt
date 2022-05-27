package com.example.programmingbasics.api

import com.example.programmingbasics.api.data_objects.LoginRequest
import com.example.programmingbasics.api.responses.LessonResponse
import com.example.programmingbasics.api.responses.SectionsResponse
import com.example.programmingbasics.api.responses.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ProgrammingBasicsApiService {
  @Headers("Content-Type: application/json")
  @POST(Constants.SIGN_IN_URL)
  fun signIn(@Body body: LoginRequest): Call<UserResponse>

  @GET(Constants.SECTIONS_URL)
  suspend fun getSections(@Header("Authorization") token: String): SectionsResponse

  @GET(Constants.LESSON_URL)
  suspend fun getLesson(@Path("id") lessonId: Int, @Header("Authorization") token: String): LessonResponse
}
