package com.example.rotationwheel

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheelview.WheelView


//import java.util.*

class mainOrder : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<menuList>()
    private lateinit var adapter: menuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        val wheelView = findViewById<WheelView>(R.id.wheel_view)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = menuAdapter(mList)
        recyclerView.adapter = adapter

        wheelView.titles = listOf("파스타", "마라탕", "오늘의 메뉴 A", "오늘의 메뉴 B", "5", "6")

        wheelView.selectListener = { // selected position
            Toast.makeText(this,"selected on ${it}",Toast.LENGTH_SHORT).show()
        }

        adapter.setItemClickListener(object : menuAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Toast.makeText(v.context, "${mList[position].menu} 클릭", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addDataToList() {
        mList.add(
            menuList(
                "Java", 1,
                R.drawable.java,
                "Java is a high-level, class-based, object-oriented programming"
            )
        )
        mList.add(
            menuList(
                "Kotlin", 2,
                R.drawable.kotlin,
                "Kotlin is a cross-platform, statically typed, general-purpose programming"

            )
        )
        mList.add(
            menuList(
                "HTML", 3,
                R.drawable.html
            )
        )

    }

}