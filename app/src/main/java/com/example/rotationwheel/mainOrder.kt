package com.example.rotationwheel

import android.app.slice.Slice
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rotationwheel.databinding.ActivityMainBinding
import com.example.wheelview.WheelView


class mainOrder : AppCompatActivity() {
    //menu 목록
    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<menuList>()
    private lateinit var adapter: menuAdapter

    //cart
    private lateinit var cartlistView: RecyclerView
    private var cartmenu_list = ArrayList<cartList>()
    private lateinit var cartListViewAdapter : cartAdapter

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

        //cart
        cartlistView = findViewById(R.id.cartlistView)
        cartlistView.setHasFixedSize(true)
        cartlistView.layoutManager = LinearLayoutManager(this)
        cartListViewAdapter = cartAdapter(cartmenu_list)
        cartlistView.adapter = cartListViewAdapter

        //var cartDrawer :SlidingDrawer = findViewById(R.id.cartLayout)

        wheelView.titles = listOf("파스타", "마라탕", "오늘의 메뉴 A", "오늘의 메뉴 B", "5", "6")

        wheelView.selectListener = { // selected position
            Toast.makeText(this,"selected on ${it}",Toast.LENGTH_SHORT).show()
        }

        adapter.setItemClickListener(object : menuAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Toast.makeText(v.context, "${mList[position].menu} 클릭", Toast.LENGTH_SHORT).show()

                //담기버튼
                wheelView.centerClickListener = {
                    cart(mList[position].menu)
                    Toast.makeText(v.context, "담음", Toast.LENGTH_SHORT).show()
                    Toast.makeText(v.context, "${cartmenu_list.size}", Toast.LENGTH_SHORT).show()

                    //카트 변동사항 업데이트
                    cartListViewAdapter.notifyDataSetChanged()
                }
            }
        })
        /*
        cartListViewAdapter.setOnCartListChangeListener(object : cartAdapter.OnCartListChangfeListener{
            override fun onCartListChanged() {
                if(cartmenu_list.isEmpty()) cartDrawer.visibility = View.GONE
                else cartDrawer.visibility = View.VISIBLE
            }
        })

         */

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

    private fun cart(menu_name : String) {       //장바구니 3개까지 담을수있음
        /*
        if (Cart.num >= 3) {
            Toast.makeText(this, "음식 3개까지 주문 가능", Toast.LENGTH_SHORT).show()
            return
        }

         */


        //DB 없이 불러오는 예시
        cartmenu_list.add(cartList(menu_name, 1, 1, 1, 1))
        //선택한 메뉴 가격, 메뉴 아이디와 가게 ID 불러와야함
        //selectedMenuId, selectedMenuPrice, restID
        var search = cartmenu_list.find { it.menu == menu_name }


        /*
        if (search == null) {
            cartmenu_list.add(cartList(menu_name, selectedMenuId, selectedMenuPrice, 2, restID))
        } else {
            search.menuCount++
        }

         */

    }

        /*
        val call = MenuService!!.getMenuInfo(menu_name)  //메뉴 이름 넣어서 조회
        call.enqueue(object : Callback<List<Menu>>{
            override fun onResponse(call: Call<List<Menu>>, response: Response<List<Menu>>) {
                if (!response.isSuccessful) {
                    return
                }
                menu_list = response.body()!!
                for (menu in menu_list) {
                    var content = ""
                    content += """
                            메뉴 : ${menu.menu_name}
                            가격 : ${menu.price}


                        """.trimIndent()
                    textViewCart.append(content)
                    Cart.menu.add(menu.menu_name)
                    Cart.rest.add(menu.restaurant)
                    Cart.price.add(menu.price)
                    Cart.total_price += menu.price
                    Cart.num++
                    Toast.makeText(this@OrderActivity, "담기 완료", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Menu>>, t: Throwable) {
                Log.d("Debug", "onFailure 실행$t")
            }
        })
        */


    /*
    private fun cartPlus(cart_num : Int) {
        cart(Cart.menu[cart_num])
    }
    private fun cartUpdate() {
        cartlistView.text = ""
        var content = ""
        for(i in 0..(Cart.num-1)) {
            content += """
                    메뉴 : ${Cart.menu[i]}
                    가격 : ${Cart.price[i]}
                    
                """.trimIndent()
        }
        textViewCart.append(content)
    }


    fun cartDelete(cart_num : Int) {
        Cart.num--
        Cart.total_price -= Cart.price[cart_num]
        Cart.menu.removeAt(cart_num)
        Cart.rest.removeAt(cart_num)
        Cart.price.removeAt(cart_num)
        //cartUpdate()
    }

    */

}