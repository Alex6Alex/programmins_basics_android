package com.example.programmingbasics.api

import com.example.programmingbasics.api.data_objects.LoginRequest
import com.example.programmingbasics.api.data_objects.User
import com.example.programmingbasics.api.responses.LessonResponse
import com.example.programmingbasics.api.responses.SectionsResponse
import retrofit2.http.*

interface ProgrammingBasicsApiService {
  @POST(Constants.SIGN_IN_URL)
  @FormUrlEncoded
  suspend fun signIn(@Body request: LoginRequest): User

  @GET(Constants.SECTIONS_URL)
  suspend fun getSections(@Header("Authorization") token: String): SectionsResponse

  @GET(Constants.LESSON_URL)
  suspend fun getLesson(@Path("id") lessonId: Int, @Header("Authorization") token: String): LessonResponse
}
