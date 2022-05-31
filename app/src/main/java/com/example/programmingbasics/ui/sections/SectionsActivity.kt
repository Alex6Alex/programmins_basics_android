package com.example.programmingbasics.ui.sections

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.programmingbasics.R
import com.example.programmingbasics.api.data_objects.Section
import com.example.programmingbasics.ui.lesson.LessonActivity
import com.example.programmingbasics.ui.login.LoginActivity
import com.example.programmingbasics.viewModelsFactory

class SectionsActivity : AppCompatActivity() {
  private val sectionViewModel by viewModelsFactory {
    val token = getSharedPreferences("Auth", Context.MODE_PRIVATE).getString("token", null)
    SectionViewModel(token)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sections)

    val rootLayout = findViewById<LinearLayout>(R.id.constraint_layout)

    sectionViewModel.records.observe(this) { records ->
      for (section in records.sortedBy { record -> record.order }) {
        addSectionToView(section, rootLayout)
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.custom_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
    R.id.log_out -> {
      getSharedPreferences("Auth", Context.MODE_PRIVATE).edit { putString("token", null) }
      startActivity(Intent(this@SectionsActivity, LoginActivity::class.java))
      finish()

      true
    }

    else -> super.onOptionsItemSelected(item)
  }

  fun startLearning(view: View) {
    val intent = Intent(this, LessonActivity::class.java).apply {
      putExtra(EXTRA_MESSAGE, 1)
    }
    startActivity(intent)
  }

  fun openSubject(view: View) {
    val intent = Intent(this, LessonActivity::class.java).apply {
      putExtra(EXTRA_MESSAGE, view.id)
    }
    startActivity(intent)
  }

  private fun addSectionToView(section: Section, viewGroup: ViewGroup) {
    val view = layoutInflater.inflate(R.layout.section, viewGroup, false)

    view.findViewById<TextView>(R.id.number).text = section.order.toString()
    view.findViewById<TextView>(R.id.name).text = section.name
    view.findViewById<TextView>(R.id.description).text = section.description

    viewGroup.addView(view)

    section.lessons.sortedBy { lesson -> lesson.order }.forEach { lesson ->
      val lessonView = layoutInflater.inflate(R.layout.lesson, viewGroup, false)
      lessonView.id = lesson.id.toInt()

      lessonView.findViewById<TextView>(R.id.name).text =
        getString(R.string.lesson_name, section.order, lesson.order, lesson.name)
      lessonView.findViewById<TextView>(R.id.description).text = lesson.description

      viewGroup.addView(lessonView)
    }
  }
}
