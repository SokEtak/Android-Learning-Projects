package com.example.napeoleongame

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var tv: TextView
    private lateinit var inputField: TextInputEditText
    private lateinit var btn: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv = findViewById(R.id.tv)
        inputField = findViewById(R.id.textInputEditText)
        btn = findViewById(R.id.btn)

        btn.setOnClickListener {
            val t = inputField.text.toString()
            val score = calculateCustomScore(t)
            tv.text = "The word \"$t\" has $score scores so lover is: ${getLoverName(score)}"
        }
    }

    private fun calculateCustomScore(word: String): Int {
        return word.uppercase().sumOf { letter ->
            val letterPosition = (letter - 'A' + 1)
            if (letterPosition % 9 == 0) 9 else letterPosition % 9
        }
    }

    private fun getLoverName(score: Int): String {
        val bullyWords: String = "u deserve to be alone"
        val loverName: String = when {
            score in 0..5 -> bullyWords
            score in 6..10 -> "Vanna"
            score in 11..15 -> "Chinda"
            score in 16..20 -> "Lin"
            score in 21..25 -> "Nari"
            score in 26..30 -> "Keav"
            score in 31..35 -> "Sopha"
            score in 36..40 -> "Nani"
            score in 41..45 -> "Srey"
            score in 46..50 -> "Thavy"
            score in 51..55 -> "Phneak"
            score in 56..60 -> "Krin"
            score in 61..65 -> "Mali"
            score in 66..70 -> "Loy"
            score in 71..75 -> "Samna"
            score in 76..80 -> "Marady"
            score in 81..85 -> "Serey"
            score in 86..90 -> "Teap"
            score in 91..95 -> "Chen"
            score in 96..100 -> "Sophea"
            score in 101..105 -> "Sros"
            score in 106..110 -> "Vannak"
            score in 111..115 -> "Chea"
            score in 116..120 -> "Chakra"
            score in 121..125 -> "Neary"
            score in 126..130 -> "Sreyneang"
            score in 131..135 -> "Pheak"
            score in 136..140 -> "Sokunthea"
            score in 141..145 -> "Kanha"
            score in 146..150 -> "Rachana"
            score in 151..155 -> "Sreynath"
            score in 156..160 -> "Borey"
            score in 161..165 -> "Dara"
            score in 166..170 -> "Makara"
            score in 171..175 -> "Visal"
            score in 176..180 -> "Sangha"
            score in 181..185 -> "Rattanak"
            score in 186..190 -> "Sakhan"
            score in 191..195 -> "Sophorn"
            score in 196..200 -> "Sokpheng"
            else -> bullyWords
        }

        return loverName
    }


}
