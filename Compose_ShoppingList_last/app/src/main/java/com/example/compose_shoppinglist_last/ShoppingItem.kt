package com.example.compose_shoppinglist_last

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data model for shopping items, includes unique id, item quantity, and name.
data class ShoppingItem(val id: Int, var qty: Int, var name: String, val isEdit: Boolean = false)

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    // List of shopping items stored in mutable state
    var shoppingItems by remember { mutableStateOf(listOf<ShoppingItem>()) }

    // State to control the visibility of the Add Item dialog
    var showAlertDialog by remember { mutableStateOf(false) }

    // A counter to generate unique item IDs for each new item
    var nextItemId by remember { mutableIntStateOf(1) }

    // States for handling item name and quantity input
    var itemName by remember { mutableStateOf("") }

    var itemQuantity by remember { mutableIntStateOf(1) }

    // A state for handling the item marked for deletion
    var itemToDelete by remember { mutableStateOf<ShoppingItem?>(null) }

    // Container for the entire layout
    Column(
        modifier = Modifier.fillMaxSize(), // The column takes up the full available space
        horizontalAlignment = Alignment.CenterHorizontally, // Center the content horizontally
        verticalArrangement = Arrangement.Top // Place items at the top of the screen
    ) {
        // Button to trigger the Add Item dialog
        Button(
            onClick = { showAlertDialog = true }, // Show the dialog when clicked
            modifier = Modifier.padding(16.dp) // Add padding around the button
        ) {
            Text("Add Item") // Button text
        }

        // LazyColumn to display the list of shopping items
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Allow LazyColumn to take up available vertical space
                .padding(20.dp) // Add padding around the list
        ) {
            items(shoppingItems) { item -> // Iterate through the shoppingItems list
                if (item.isEdit) {
                    // Show editor for items in edit mode
                    ShoppingItemEditor(
                        item = item,
                        onEditComplete = { editedName, editedQuantity ->
                            // Update item when edit is complete
                            shoppingItems = shoppingItems.map {
                                if (it.id == item.id) {
                                    it.copy(name = editedName, qty = editedQuantity, isEdit = false)
                                } else {
                                    it
                                }
                            }
                        }
                    )
                } else {
                    // Display regular view when item is not in edit mode
                    ShoppingListItem(
                        item = item,
                        onEditClick = {
                            // Switch to edit mode for the selected item
                            shoppingItems = shoppingItems.map { it.copy(isEdit = it.id == item.id) }
                        },
                        onDeleteClick = {
                            // Mark item for deletion and trigger delete confirmation
                            itemToDelete = item
                        }
                    )
                }
            }
        }

        // Add Item Dialog to input the new item's details
        if (showAlertDialog) {
            AlertDialog(
                onDismissRequest = { showAlertDialog = false }, // Dismiss dialog on outside touch
                title = { Text("Add New Item") },
                text = {
                    Column {
                        // Input for item name
                        Text("Enter the item name:")
                        OutlinedTextField(
                            value = itemName,
                            onValueChange = { itemName = it }, // Update item name state
                            singleLine = true, // Prevent multiline text input
                            label = { Text("Name") },
                            modifier = Modifier
                                .fillMaxWidth() // Make the text field take full width
                                .padding(10.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp)) // Add vertical space between fields
                        // Input for item quantity
                        Text("Enter the item quantity:")
                        OutlinedTextField(
                            value = itemQuantity.toString(),
                            onValueChange = { itemQuantity = it.toIntOrNull() ?: 1 }, // Handle invalid quantity input
                            label = { Text("Quantity") },
                            modifier = Modifier
                                .fillMaxWidth() // Full-width input field
                                .padding(10.dp)
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (itemName.isNotBlank()) {
                            // Add the new item to the list if name is not empty
                            shoppingItems = shoppingItems + ShoppingItem(
                                id = nextItemId,
                                name = itemName,
                                qty = itemQuantity
                            )
                            nextItemId++ // Increment item ID for next item
                        }
                        showAlertDialog = false // Close dialog after adding item
                        itemName = "" // Reset name input
                        itemQuantity = 1 // Reset quantity input
                    }) {
                        Text("Confirm") // Button to confirm adding the item
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        // Close the dialog without adding any item
                        showAlertDialog = false
                        itemName = ""
                        itemQuantity = 1
                    }) {
                        Text("Cancel") // Button to cancel the action
                    }
                }
            )
        }

        // Confirm deletion dialog when item is selected for deletion
        itemToDelete?.let { item ->
            AlertDialog(
                onDismissRequest = { itemToDelete = null }, // Dismiss delete dialog
                title = { Text("Confirm Deletion") },
                text = { Text("Are you sure you want to delete ${item.name}?") }, // Display item name
                confirmButton = {
                    TextButton(onClick = {
                        // Remove item from shopping list
                        shoppingItems = shoppingItems - item
                        itemToDelete = null // Reset the delete state
                    }) {
                        Text("Yes, Delete") // Confirm deletion button
                    }
                },
                dismissButton = {
                    TextButton(onClick = { itemToDelete = null }) {
                        Text("No, Keep") // Button to cancel deletion
                    }
                }
            )
        }
    }
}

// Composable function for editing a shopping item
@Composable
fun ShoppingItemEditor(
    item: ShoppingItem,
    onEditComplete: (String, Int) -> Unit // Callback to handle the edited item
) {
    var editedName by remember { mutableStateOf(item.name) } // State for edited name
    var editedQty by remember { mutableStateOf("${item.qty}") } // State for edited quantity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly // Space the elements evenly
    ) {
        Column {
            // Text field to edit the item name
            BasicTextField(
                value = editedName,
                onValueChange = { editedName = it },
                singleLine = true, // Prevent multiline text input
                modifier = Modifier.padding(8.dp) // Add padding
            )
            // Text field to edit the item quantity
            BasicTextField(
                value = editedQty,
                onValueChange = { editedQty = it },
                singleLine = true, // Single-line input for quantity
                modifier = Modifier.padding(8.dp) // Padding for the input field
            )
        }
        Button(
            onClick = {
                // Callback when editing is complete, pass the edited name and quantity
                onEditComplete(editedName, editedQty.toIntOrNull() ?: 1)
            }
        ) {
            Text("Save") // Button text
        }
    }
}

// Composable function for displaying a single shopping item
@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit, // Callback to enter edit mode
    onDeleteClick: () -> Unit // Callback to initiate deletion
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, Color.Green), shape = RoundedCornerShape(25)), // Border around item
        verticalAlignment = Alignment.CenterVertically, // Align items vertically
        horizontalArrangement = Arrangement.SpaceBetween // Space out the elements
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Display the item name
            Text(text = item.name)
            // Display the item quantity
            Text(text = "Quantity - ${item.qty}")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Button to edit the item
            IconButton(onClick = { onEditClick() }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon")
            }
            // Button to delete the item
            IconButton(onClick = { onDeleteClick() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListAppPreview() {
    ShoppingListApp() // Preview of the ShoppingListApp composable
}
