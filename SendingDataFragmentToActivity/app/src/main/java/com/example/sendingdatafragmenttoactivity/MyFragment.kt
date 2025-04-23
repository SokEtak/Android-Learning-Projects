package com.example.sendingdatafragmenttoactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class MyFragment : Fragment() {
    private lateinit var  etName: EditText
    private lateinit var  etEmail: EditText
    private lateinit var  sendBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_my, container, false)
        etName = view.findViewById(R.id.etName)
        etEmail = view.findViewById(R.id.etEmail)
        sendBtn = view.findViewById(R.id.sendButton)

        sendBtn.setOnClickListener {
            var username = etName.text.toString()
            var email = etEmail.text.toString()
            /*val mainActivity =activity as MainActivity
            mainActivity.takeData(username, email)*/
            //work the same
            (activity as MainActivity).takeData(username, email)
            etName.text=null
            etEmail.text=null
        }

        // Inflate the layout for this fragment
        return view
    }
}