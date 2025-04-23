package com.example.gridview
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(private val context: Context, private val items: ArrayList<Item>) : BaseAdapter() {

    // Define a custom listener interface for item clicks
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    // Provide a method to set the listener
    fun setOnItemClickListener(listener: MainActivity) {
        this.listener = listener
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            // Inflate the grid item layout
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.custom_layout, parent, false)
        }

        // Get the current item
        val item = items[position]

        // Set the image and text for the grid item
        val imageView: ImageView = view!!.findViewById(R.id.itemImage)
        val textView: TextView = view.findViewById(R.id.itemName)

        imageView.setImageResource(item.imageResId) // Set the image resource
        textView.text = item.name // Set the name

        // Set the click listener for the grid item
        view.setOnClickListener {
            // Notify the activity about the item click
            listener?.onItemClick(position)
        }

        return view
    }
}


