package com.example.rotationwheel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.wheelview.WheelView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        val wheelView = findViewById<WheelView>(R.id.wheel_view)
        /*
        wheelView.setOnClickListener {
            Toast.makeText(this,wheelView.focusedIndex,Toast.LENGTH_SHORT).show()
        }
        */

        wheelView.titles = listOf("First", "Second", "Third", "Fourth")

    }
}