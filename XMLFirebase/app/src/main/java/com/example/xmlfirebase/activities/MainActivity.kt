package com.example.xmlfirebase.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment // Import Fragment
import com.example.xmlfirebase.R
import com.example.xmlfirebase.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

// Import your new fragment classes
import com.example.xmlfirebase.UserListFragment
// Make sure to create these dummy fragments as well.

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    private var currentFragment: Fragment? = null // Keep track of the current fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setSupportActionBar(mainBinding.toolbar)
        firebaseAuth = FirebaseAuth.getInstance()

        setupDrawer()
        setupBottomNavigation() // New: Setup bottom navigation

        // Initialize the AuthStateListener
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            if (user == null) {
                Toast.makeText(this, "Your account has been disabled or deleted. Please log in again.", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                user.reload().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (!user.isEmailVerified) {
                            Toast.makeText(this, "Please verify your email.", Toast.LENGTH_SHORT).show()
                            firebaseAuth.signOut()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            // User is active and verified, proceed with app functionality
                            // Load the default fragment if not already loaded (e.g., after orientation change)
                            if (savedInstanceState == null) {
                                mainBinding.bottomNavigation.selectedItemId = R.id.bottom_nav_user_list // Set default selected item
                            }
                        }
                    } else {
                        Toast.makeText(this, "Account disabled or deleted.", Toast.LENGTH_LONG).show()
                        firebaseAuth.signOut()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }

        // Handle Floating Action Button click - this now works for the fragment
        mainBinding.fab.setOnClickListener {
            // Since FAB is in MainActivity, it can trigger actions for the current fragment
            // Or it can always open the AddUserActivity directly
            startActivity(Intent(this, AddUserActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    private fun setupDrawer() {
        drawerLayout = mainBinding.root as DrawerLayout
        navigationView = mainBinding.navView

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            mainBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    // New function to setup bottom navigation
    private fun setupBottomNavigation() {
        mainBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_user_list -> {
                    loadFragment(UserListFragment())
                    mainBinding.toolbar.title = "User List"
                    true
                }

                else -> false
            }
        }
    }

    // New helper function to load fragments into the FrameLayout
    private fun loadFragment(fragment: Fragment) {
        currentFragment = fragment // Update current fragment reference
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // R.id.fragment_container is your FrameLayout
            .commit()
    }


    // The `onCreateOptionsMenu` and `onOptionsItemSelected` methods for toolbar menu
    // will now need to interact with the *current fragment* if actions are specific
    // to the fragment (like search and delete all users).
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_delete, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.queryHint = "Search users..."
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                // Delegate search query to the current fragment if it's a UserListFragment
                (currentFragment as? UserListFragment)?.filterUsers(newText ?: "")
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true

        return when (item.itemId) {
            R.id.deleteAll -> {
                // Delegate delete all action to the current fragment if it's a UserListFragment
                (currentFragment as? UserListFragment)?.deleteAllUsers()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle drawer item clicks.
        // You might want to switch to a specific bottom nav item, or load a different fragment.
        when (item.itemId) {
            R.id.nav_home -> {
                // If "Home" in drawer leads to the User List, sync bottom nav
                mainBinding.bottomNavigation.selectedItemId = R.id.bottom_nav_user_list
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show()
                // Example: loadFragment(ProfileFragment()) if you have one
            }
//            R.id.nav_settings -> {
//                // If "Settings" in drawer leads to the Settings fragment, sync bottom nav
//                mainBinding.bottomNavigation.selectedItemId = R.id.bottom_nav_settings
//                drawerLayout.closeDrawer(GravityCompat.START)
//                startActivity(Intent(this, SettingActivity::class.java))
//            }

            R.id.nav_settings -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                startActivity(Intent(this, SettingActivity::class.java))
            }


            R.id.nav_about -> {
                // If "About Us" in drawer leads to the About fragment, sync bottom nav
                mainBinding.bottomNavigation.selectedItemId = R.id.bottom_nav_about
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_logout -> {
                firebaseAuth.signOut()
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}