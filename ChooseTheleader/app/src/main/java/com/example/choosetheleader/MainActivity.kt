package com.example.choosetheleader

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var amountOfaGroupNumber: EditText // Input field for number of groups
    private lateinit var randomBtn: Button // Button to trigger randomization
    private lateinit var tv: TextView // TextView to display the groups

    // Updated list of members
    private val members = arrayListOf(
        "Rong", "Somnang", "Ina",
        "MengLim", "Tit Pheakdey", "Puy",
        "KhunLun", "Rorny", "Etak",
        "Saneurt", "Gecch", "Bunly"
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set up window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Link the UI components to the variables
        amountOfaGroupNumber = findViewById(R.id.editTextText)
        randomBtn = findViewById(R.id.button)
        tv = findViewById(R.id.textView)

        // Set up click listener for the button
        randomBtn.setOnClickListener {
            // Get the number of groups from user input
            val amountOfGroupMember = amountOfaGroupNumber.text.toString().toIntOrNull()

            // Check if the input is valid
            if (amountOfGroupMember == null || amountOfGroupMember <= 0) {
                tv.text = "Please enter a valid number of group members."
                return@setOnClickListener // Exit if input is invalid
            }

            // Create a list to hold groups
            val groups = MutableList(amountOfGroupMember) { mutableListOf<String>() }
            // Make a copy of the members list so we can modify it
            val membersCopy = members.toMutableList()

            // Distribute members evenly among the groups
            while (membersCopy.isNotEmpty()) {
                for (g in 0 until amountOfGroupMember) { // Loop through each group
                    if (membersCopy.isEmpty()) break // Exit if there are no more members
                    // Pick a random member from the list
                    val randomN = Random.nextInt(membersCopy.size)
                    // Add the selected member to the current group
                    groups[g].add(membersCopy.removeAt(randomN)) // Remove the member from the copy
                }
            }

            // Prepare the output string for display
            val output = StringBuilder("<b>Groups:</b><br>")
            for (i in groups.indices) {
                // Pick a random leader from the current group
                val leader = if (groups[i].isNotEmpty()) {
                    groups[i][Random.nextInt(groups[i].size)]
                } else {
                    "No members"
                }

                // Adding group number and leader in red
                output.append("<b>Group ${i + 1}</b> (Leader: <font color='red'>$leader</font>): ")
                    .append(groups[i].joinToString(", ") { member -> member }) // Join members with commas
                    .append("<br><br>") // Add spacing between groups
            }

            // Display the groups in the TextView using Html
            tv.text = Html.fromHtml(output.toString().trim(), Html.FROM_HTML_MODE_LEGACY) // Trim to remove any trailing whitespace
        }
    }
}
