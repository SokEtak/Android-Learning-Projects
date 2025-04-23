package com.example.dialogfragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {
    lateinit var name:EditText
    lateinit var age:EditText
    lateinit var ok : Button
    lateinit var cancel : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_dialog, container, false)

        name = view.findViewById(R.id.editTextName)
        age = view.findViewById(R.id.editTextAge)
        cancel = view.findViewById(R.id.cancelBtn)
        ok = view.findViewById(R.id.okBtn)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        cancel.setOnClickListener {
            dialog!!.dismiss()
        }

        ok.setOnClickListener {
            val userName :String = name.text.toString()
            val userAge : Int = age.text.toString().toInt()
            val mainActivity : MainActivity = activity as MainActivity
            mainActivity.getData(userName,userAge )
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}