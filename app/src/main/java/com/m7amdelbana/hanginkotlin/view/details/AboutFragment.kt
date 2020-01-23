package com.m7amdelbana.hanginkotlin.view.details


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.m7amdelbana.hanginkotlin.R

class AboutFragment : Fragment() {
    companion object {
        fun newInstance():AboutFragment =
            AboutFragment()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }


}
