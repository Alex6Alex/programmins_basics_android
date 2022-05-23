package com.example.programmingbasics

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.programmingbasics.view_models.LessonViewModel
import com.google.android.material.tabs.TabLayoutMediator

class LessonActivity : AppCompatActivity() {
  private val lessonViewModel: LessonViewModel by viewModelsFactory {
    LessonViewModel(intent.getIntExtra(EXTRA_MESSAGE, 0))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_lesson)

    lessonViewModel.record.observe(this) { lesson ->
      val lessonPartsAdapter = LessonPartsAdapter(this, lesson.lessonUnits.orEmpty())

      val viewPager = findViewById<ViewPager2>(R.id.pager)
      viewPager.adapter = lessonPartsAdapter

      TabLayoutMediator(findViewById(R.id.tab_layout), viewPager) { tab, _ ->
        tab.setIcon(R.drawable.ic_lesson_section)
      }.attach()

      supportFragmentManager.setFragmentResultListener("buttonSwipe", this) { _, _ ->
        viewPager.currentItem += 1
      }
      supportFragmentManager.setFragmentResultListener("endLesson", this) { _, _ ->
        finish()
      }
    }
  }
}
