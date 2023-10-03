package com.example.rotationwheel

data class menuList(
    val menu: String,
    val menuID: Int,
    val menuPic: Int,
    var desc: String? = null,
    var isExpandable: Boolean = false
)
