package com.m7amdelbana.hanginkotlin.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.m7amdelbana.hanginkotlin.R
class ReviewFragment : Fragment() {


    companion object {
        fun newInstance():ReviewFragment =
            ReviewFragment()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_review, container, false)
}
