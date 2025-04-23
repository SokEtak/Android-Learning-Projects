package com.example.bmicalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.time.times


class BMIFragment : Fragment() {

    private lateinit var tvResult : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_b_m_i, container, false)
        tvResult = view.findViewById(R.id.tvResult)
        //getting data from activity
        val weight = arguments?.getInt("weight")!!.toInt()
        val height = arguments?.getInt("height")!!.toInt()
        val result : Double = ((weight*10000)/(height*height)).toDouble()

        tvResult.text = "Your BMI: $result"
        // Inflate the layout for this fragment
        return view
    }


}