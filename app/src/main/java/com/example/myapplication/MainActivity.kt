package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.presentation.list.PhotoListActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Navigate to PhotoListActivity
        val intent = Intent(this, PhotoListActivity::class.java)
        startActivity(intent)
        finish()
    }
}