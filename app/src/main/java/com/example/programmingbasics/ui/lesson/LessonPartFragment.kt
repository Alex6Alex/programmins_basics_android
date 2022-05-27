package com.example.programmingbasics.ui.lesson

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResult
import com.example.programmingbasics.R

open class LessonPartFragment(private val btnFragment: Fragment) : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val layout = inflater.inflate(R.layout.fragment_lesson_part, container, false)

    val fragmentTransaction = childFragmentManager.beginTransaction()
    fragmentTransaction.add(layout.id, btnFragment)
    fragmentTransaction.commit()

    return layout
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    arguments?.takeIf { it.containsKey("content") }?.apply {
      val contentView = view.findViewById<TextView>(R.id.lesson_section)

      contentView.text = Html.fromHtml(getString("content"), Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV)
      contentView.movementMethod = LinkMovementMethod.getInstance();
    }

    childFragmentManager.setFragmentResultListener("buttonSwipe", this) { key, bundle ->
      setFragmentResult(key, bundle)
    }
    childFragmentManager.setFragmentResultListener("endLesson", this) { key, bundle ->
      setFragmentResult(key, bundle)
    }
  }
}