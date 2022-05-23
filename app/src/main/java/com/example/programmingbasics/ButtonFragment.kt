package com.example.programmingbasics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

class ButtonFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_button, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    view.findViewById<Button>(R.id.lesson_part_button).setOnClickListener {
      setFragmentResult("buttonSwipe", bundleOf("swipeAction" to true))
    }
  }
}