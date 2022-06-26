package com.example.programmingbasics.ui.lesson

import android.content.Context
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.programmingbasics.R
import com.example.programmingbasics.api.data_objects.LessonUnit
import com.example.programmingbasics.viewModelsFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LessonActivity : AppCompatActivity() {
  private val lessonViewModel by viewModelsFactory {
    val token = getSharedPreferences("Auth", Context.MODE_PRIVATE).getString("token", null)
    LessonViewModel(intent.getIntExtra(EXTRA_MESSAGE, 0), token)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_lesson)

    lessonViewModel.record.observe(this) { lesson ->
      val lessonUnits = lesson.lessonUnits.orEmpty().sortedBy { unit -> unit.order }
      val lessonPartsAdapter = LessonPartsAdapter(this, lessonUnits)

      val viewPager = findViewById<ViewPager2>(R.id.pager)
      viewPager.adapter = lessonPartsAdapter

      TabLayoutMediator(findViewById(R.id.tab_layout), viewPager) { tab, tab_number ->
        if (lessonUnits[tab_number].isPassed) tab.setIcon(R.drawable.ic_lesson_section_passed)
        else tab.setIcon(R.drawable.ic_lesson_section)
      }.attach()

      supportFragmentManager.setFragmentResultListener("buttonSwipe", this) { _, _ ->
        markUnitAsRead(lessonUnits[viewPager.currentItem], viewPager.currentItem)
        viewPager.currentItem += 1
      }
      supportFragmentManager.setFragmentResultListener("endLesson", this) { _, _ ->
        markUnitAsRead(lessonUnits[viewPager.currentItem], viewPager.currentItem)
        finish()
      }
    }
  }

  private fun markUnitAsRead(lessonUnit: LessonUnit, currentItemNum: Int) {
    lessonViewModel.markUnitAsRead(lessonUnit)
    findViewById<TabLayout>(R.id.tab_layout).getTabAt(currentItemNum)!!.setIcon(R.drawable.ic_lesson_section_passed)
  }
}
