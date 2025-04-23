package com.example.senddatafromfragmenttofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SecondFragment : Fragment() {
    lateinit var tvName: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        tvName = view.findViewById(R.id.tvName)
        tvName.text = arguments?.getString("username")
        // Inflate the layout for this fragment
        return view
    }

}