package com.example.programmingbasics.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.programmingbasics.R
import com.example.programmingbasics.api.ProgrammingBasicsApi
import com.example.programmingbasics.api.data_objects.LoginRequest
import com.example.programmingbasics.api.data_objects.User
import com.example.programmingbasics.api.responses.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
  private val _loginForm = MutableLiveData<LoginFormState>()
  private val _loginResult = MutableLiveData<LoginResult>()

  val loginFormState: LiveData<LoginFormState> = _loginForm
  val loginResult: LiveData<LoginResult> = _loginResult

  fun login(email: String, password: String) {
    val singInData = LoginRequest(User(email = email, password = password))

    ProgrammingBasicsApi.client.signIn(singInData).enqueue(object : Callback<UserResponse> {
      override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
        if (response.isSuccessful) {
          val user = response.body()!!.data

          _loginResult.value = LoginResult(success = LoggedInUserView(displayName = user.email, token = user.token!!))
        } else {
          _loginResult.value = LoginResult(error = R.string.login_failed)
        }
      }

      override fun onFailure(call: Call<UserResponse>, t: Throwable) {
        _loginResult.value = LoginResult(error = R.string.login_failed)
      }
    })
  }

  fun loginDataChanged(username: String, password: String) {
    if (!isUserNameValid(username)) {
      _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
    } else if (!isPasswordValid(password)) {
      _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
    } else {
      _loginForm.value = LoginFormState(isDataValid = true)
    }
  }

  private fun isUserNameValid(username: String): Boolean {
    return if (username.contains('@')) {
      Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
      username.isNotBlank()
    }
  }

  private fun isPasswordValid(password: String): Boolean {
    return password.length > 5
  }
}