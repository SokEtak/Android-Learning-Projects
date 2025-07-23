package com.example.xmlfirebase // Create this package

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlfirebase.adapters.UserAdapter
import com.example.xmlfirebase.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// You'll need to pass the search query from MainActivity if you want
// filtering to persist across fragment re-creations, or manage it internally.
class UserListFragment : Fragment() {

    private lateinit var reference: DatabaseReference
    private val userList = mutableListOf<User>()
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase DB reference (local to this fragment)
        reference = FirebaseDatabase.getInstance().getReference("User")

        userAdapter = UserAdapter(requireContext(), userList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = userAdapter

        setItemTouchHelper(recyclerView) // Pass recyclerView to the function
        retrieveDataFromDatabase()
    }

    // Function to handle search query from MainActivity
    fun filterUsers(query: String) {
        if (::userAdapter.isInitialized) {
            userAdapter.filter(query)
        }
    }

    private fun retrieveDataFromDatabase() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(User::class.java)
                    user?.let { userList.add(it) }
                }
                userAdapter.updateList(userList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    "Failed To Retrieve Data From Realtime Database: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setItemTouchHelper(recyclerView: RecyclerView) {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val user = userList[position]
                reference.child(user.userId ?: "").removeValue().addOnCompleteListener {
                    val msg = if (it.isSuccessful) "User deleted" else "Failed to delete"
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                }
            }
        }).attachToRecyclerView(recyclerView)
    }

    // You might also need a way to trigger deleteAll from MainActivity
    // if that option is still in the toolbar and needs to affect the UserListFragment's data.
    fun deleteAllUsers() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete All Users")
            .setMessage("Are you sure you want to delete all users?")
            .setPositiveButton("OK") { _, _ ->
                reference.removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "All users were deleted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Failed to delete all users", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}