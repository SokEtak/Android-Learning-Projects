package com.example.webview2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var edt : EditText
    lateinit var searchButton : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    fun search(view: View) {
         edt = findViewById<EditText>(R.id.editTextText)
         searchButton = findViewById<ImageButton>(R.id.imageButton)
        val webView = findViewById<WebView>(R.id.wevView)
        val searchQuery = edt.text.toString()
        val googleSearchUrl = "https://www.google.com/search?q=$searchQuery"
        webView.loadUrl(googleSearchUrl)

        if(googleSearchUrl.isNotEmpty()){
            webView.settings.loadsImagesAutomatically = true
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(googleSearchUrl)
        }

    }
}