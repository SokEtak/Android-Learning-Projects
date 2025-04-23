package com.example.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    var CountryNameList = ArrayList<String>()
    var detailList = ArrayList<String>()
    var imgList = ArrayList<Int>()
    lateinit var adapter :CountryAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
         rv = findViewById(R.id.recyclerView)
         rv.layoutManager = LinearLayoutManager(this@MainActivity)
         fillRecyclerView()
        adapter = CountryAdapter(CountryNameList,detailList,imgList, context = this@MainActivity)
        rv.adapter = adapter
    }
    private fun fillRecyclerView(){
        //Title
        CountryNameList.add("Cambodia")
        CountryNameList.add("Cambodia")
        CountryNameList.add("Cambodia")
        CountryNameList.add("Cambodia")
        CountryNameList.add("England")
        CountryNameList.add("Germany")
        CountryNameList.add("Laos")
        CountryNameList.add("Singapore")
        CountryNameList.add("Thailand")
        CountryNameList.add("USA")
        CountryNameList.add("Vietnam")
        CountryNameList.add("Som Nang kon Khmer")
        //Description
        detailList.add("This is Cambodia flag.")
        detailList.add("This is Cambodia flag.")
        detailList.add("This is Cambodia flag.")
        detailList.add("This is Cambodia flag.")
        detailList.add("This is England flag.")
        detailList.add("This is Germany flag.")
        detailList.add("This is Laos flag.")
        detailList.add("This is Singapore flag.")
        detailList.add("This is Thailand flag.")
        detailList.add("This is USA flag.")
        detailList.add("This is Vietnam flag.")
        detailList.add("This is Nang Nang Kon khmer")
        //resource(images)
        imgList.add(R.drawable.cambodia)
        imgList.add(R.drawable.cambodia)
        imgList.add(R.drawable.cambodia)
        imgList.add(R.drawable.cambodia)
        imgList.add(R.drawable.england)
        imgList.add(R.drawable.germany)
        imgList.add(R.drawable.laos)
        imgList.add(R.drawable.singapore)
        imgList.add(R.drawable.thai)
        imgList.add(R.drawable.usa)
        imgList.add(R.drawable.vietnam)
        imgList.add(R.drawable.img)
    }
}