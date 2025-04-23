package com.example.listfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.fragment.app.ListFragment

/*should not use ListFragment as it name (conflict)*/
class FirstFragment : ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arrayAdapter  =
            activity?.let { ArrayAdapter.createFromResource(it,R.array.cities,android.R.layout.simple_list_item_1) }
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(activity, SecondActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }
}