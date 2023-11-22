package com.example.foodfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var displayNameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find the TextView in your layout
        displayNameTextView = view.findViewById(R.id.textView)

        // Retrieve the current user from Firebase Authentication
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Check if the user is signed in
        if (currentUser != null) {
            // User is signed in, set the display name to the TextView
            displayNameTextView.text = "Hello, ${currentUser.displayName}"
        } else {
            // User is not signed in, handle this case as needed
            displayNameTextView.text = "Hello, Guest"
        }

        return view
    }
}
