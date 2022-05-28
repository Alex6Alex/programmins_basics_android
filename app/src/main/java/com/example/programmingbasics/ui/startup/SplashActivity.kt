package com.example.programmingbasics.ui.startup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.programmingbasics.ui.login.LoginActivity
import com.example.programmingbasics.ui.sections.SectionsActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val token = getSharedPreferences("Auth", Context.MODE_PRIVATE).getString("token", null)
    val activity = if (token != null) SectionsActivity::class.java else LoginActivity::class.java

    startActivity(Intent(this, activity))
    finish()
  }
}
