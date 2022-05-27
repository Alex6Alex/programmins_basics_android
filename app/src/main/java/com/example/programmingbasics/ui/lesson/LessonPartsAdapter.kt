package com.example.programmingbasics.ui.lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.programmingbasics.api.data_objects.LessonUnit

class LessonPartsAdapter(fragment: FragmentActivity, private val lessonUnits: List<LessonUnit>) : FragmentStateAdapter(fragment) {
  override fun getItemCount(): Int = lessonUnits.size

  override fun createFragment(position: Int): Fragment {
    val buttonFragment = if (position + 1 == itemCount) EndLessonButtonFragment() else ButtonFragment()
    val lessonFragment = LessonPartFragment(buttonFragment)

    lessonFragment.arguments = Bundle().apply {
      putString("content", lessonUnits[position].contentBlock)
    }
    return lessonFragment
  }
}
