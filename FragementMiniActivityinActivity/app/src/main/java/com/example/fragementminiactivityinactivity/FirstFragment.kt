package com.example.fragementminiactivityinactivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FirstFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragement_new,container,false)
    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        Log.i("Fragment","Fragment...")
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.i("Fragment","Fragment...")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        Log.i("Fragment","Fragment...")
    }
    override fun onStart() {

        super.onStart()
        Log.i("Fragment","Fragment...")
    }

    override fun onResume() {

        super.onResume()
        Log.i("Fragment","Fragment...")
    }

    override fun onPause() {

        super.onPause()
        Log.i("Fragment","Fragment...")
    }

    override fun onStop() {

        super.onStop()
        Log.i("Fragment","Fragment...")
    }
    override fun onDestroyView() {

        super.onDestroyView()
        Log.i("Fragment","Fragment...")
    }

    override fun onDestroy() {

        super.onDestroy()
        Log.i("Fragment","Fragment...")
    }

    override fun onDetach() {

        super.onDetach()
        Log.i("Fragment","Fragment...")
    }
}