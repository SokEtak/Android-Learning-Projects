package com.example.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList:ArrayList <DataClass> ) : RecyclerView.Adapter<AdapterClass.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val rvImage :ImageView = itemView.findViewById(R.id.img)
        val rvTitle :TextView = itemView.findViewById(R.id.cambodia)
    }
}
