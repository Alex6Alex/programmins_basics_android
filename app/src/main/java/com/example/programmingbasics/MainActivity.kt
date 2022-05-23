package com.example.programmingbasics

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.programmingbasics.api.data_objects.Section
import com.example.programmingbasics.view_models.SectionViewModel

class MainActivity : AppCompatActivity() {
//  private val learnThemeViewModel: LearnThemeViewModel by viewModels {
//    LearnThemeViewModelFactory((application as ProgrammingBasicsApplication).repository)
//  }
  private val sectionViewModel: SectionViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val rootLayout = findViewById<LinearLayout>(R.id.constraint_layout)

//    sectionViewModel.statusMessage.observe(this) { s -> rootLayout.findViewById<TextView>(R.id.textView2).text = s }

    sectionViewModel.records.observe(this) { records ->
      for (section in records.sortedBy { record -> record.order }) {
        addSectionToView(section, rootLayout)
      }
    }
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
    val view = layoutInflater.inflate(R.layout.theme_header, viewGroup, false)

    view.findViewById<TextView>(R.id.number).text = section.order.toString()
    view.findViewById<TextView>(R.id.name).text = section.name
    view.findViewById<TextView>(R.id.description).text = section.description

    viewGroup.addView(view)

    section.lessons.sortedBy { lesson -> lesson.order }.forEach { lesson ->
      val lessonView = layoutInflater.inflate(R.layout.theme_content, viewGroup, false)
      lessonView.id = lesson.id.toInt()

      lessonView.findViewById<TextView>(R.id.name).text =
        getString(R.string.lesson_name, section.order, lesson.order, lesson.name)
      lessonView.findViewById<TextView>(R.id.description).text = lesson.description

      viewGroup.addView(lessonView)
    }
  }
}
