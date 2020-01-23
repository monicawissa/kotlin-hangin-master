package com.m7amdelbana.hanginkotlin.view.details


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.m7amdelbana.hanginkotlin.R

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {
    companion object {
        fun newInstance():MenuFragment =
            MenuFragment()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


}
