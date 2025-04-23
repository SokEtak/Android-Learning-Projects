package com.example.senddatafromfragmenttofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager

class FirstFragment : Fragment() {
    lateinit var edtName : EditText
    lateinit var btnSend : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        edtName = view.findViewById(R.id.edtName)
        btnSend = view.findViewById(R.id.BtnSend)

        btnSend.setOnClickListener {
            //put data from first fragment to second fragment
            val username :String = edtName.text.toString()

            val bundle = Bundle()
            bundle.putString("username",username)

            var secondFragment = SecondFragment()
            secondFragment.arguments = bundle

            var fragmentManager : FragmentManager = requireActivity().supportFragmentManager //add it because we cannot use it in fragment
            var fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frame,secondFragment)
            fragmentTransaction.commit()
        }
        // Inflate the layout for this fragment
        return view
    }

}