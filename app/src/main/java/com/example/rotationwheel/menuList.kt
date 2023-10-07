package com.example.rotationwheel

data class menuList(
    val menu: String,
    val menuID: Int,
    val menuPic: Int? = null,
    var desc: String? = null,
    var isExpandable: Boolean = false
)

object Cart {     //어디둘지 몰라서 일단 여기 둠
    var menu = mutableListOf<String>()
    var rest = mutableListOf<Int>()
    var price = mutableListOf<Int>()
    var num = 0
    var total_price = 0
    fun cartReset() {
        num = 0
        total_price = 0
    }
}