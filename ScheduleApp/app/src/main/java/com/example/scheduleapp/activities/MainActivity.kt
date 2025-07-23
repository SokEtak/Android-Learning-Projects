package com.example.scheduleapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.scheduleapp.R
import com.example.scheduleapp.databinding.ActivityMainBinding
import com.example.scheduleapp.fragments.TodosFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        enableEdgeToEdge()
        setupWindowInsets()

        setupBottomNavigationView()

        // Load the initial fragment (e.g., TodosFragment) when the activity starts
        if (savedInstanceState == null) {
            replaceFragment(TodosFragment())
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(mainBinding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView = mainBinding.bottomNavigation

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(TodosFragment()) // Show the TodosFragment
                    true
                }
                R.id.nav_done -> {
                    // You would create a new fragment for completed todos (e.g., CompletedTodosFragment)
                    // and replace it here.
                     replaceFragment(AchievedTodosFragment())
                    Toast.makeText(this, "Show Completed Todos (Fragment)", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    // Helper function to replace the current fragment in the container
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            // .addToBackStack(null) // Consider adding to back stack if you want back button to navigate between tabs
            .commit()
    }
}