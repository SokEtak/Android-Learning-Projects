    package com.example.intent

    import android.annotation.SuppressLint
    import android.os.Bundle
    import android.widget.TextView
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat

    class MainActivity2 : AppCompatActivity() {
        private lateinit var result : TextView
        @SuppressLint("SetTextI18n")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main2)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            result = findViewById(R.id.textViewResult)
            val name = intent.getStringExtra("username")
            val age = intent.getIntExtra("user_Age",0) //0 is default value

            result.text="Hello $name , You're $age years old."
        }
    }