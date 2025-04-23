package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    private var CountryNameList: ArrayList<String>,
    private var detailList: ArrayList<String>,
    private var imgList: ArrayList<Int>,
    var context: Context
    ) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewCountryName: TextView = itemView.findViewById(R.id.countryName)
        var textViewCountryDetail: TextView = itemView.findViewById(R.id.countryDetail)
        var imageView: ImageView = itemView.findViewById(R.id.ivCountry)
        var card: View = itemView // Assuming your card is the root view of the item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CountryNameList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // Bind the data to the views
        holder.textViewCountryName.text = CountryNameList[position]
        holder.textViewCountryDetail.text = detailList[position]
        holder.imageView.setImageResource(imgList[position])

        // Set OnClickListener on the entire card
        holder.card.setOnClickListener {
            // Show a Toast message when the card is clicked
            Toast.makeText(context, "You clicked on ${CountryNameList[position]}", Toast.LENGTH_SHORT).show()
        }
    }
}
