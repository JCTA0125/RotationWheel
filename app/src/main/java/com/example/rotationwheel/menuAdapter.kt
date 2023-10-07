package com.example.rotationwheel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class menuAdapter (var mList: List<menuList>) :
    RecyclerView.Adapter<menuAdapter.menuViewHolder>() {

        inner class menuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val logo: ImageView = itemView.findViewById(R.id.menuPic)
            val menu: TextView = itemView.findViewById(R.id.menu)
            val menuDesc: TextView = itemView.findViewById(R.id.menuDesc)
            val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
            
            fun collapseExpandedView() {
                menuDesc.visibility = View.GONE
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): menuViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
            return menuViewHolder(view)
        }

        override fun onBindViewHolder(holder: menuViewHolder, position: Int) {

            val menu = mList[position]
            menu.menuPic?.let { holder.logo.setImageResource(it) }
            holder.menu.text = menu.menu
            holder.menuDesc.text = menu.desc

            val isExpandable: Boolean = menu.isExpandable
            holder.menuDesc.visibility = if (isExpandable) View.VISIBLE else View.GONE

            holder.constraintLayout.setOnClickListener {
                isAnyItemExpanded(position)
                menu.isExpandable = !menu.isExpandable
                notifyItemChanged(position , Unit)
                itemClickListener.onClick(it, position)
            }
        }
        interface OnItemClickListener {
            fun onClick(v: View, position: Int)
        }
        fun setItemClickListener(onItemClickListener: OnItemClickListener) {
            this.itemClickListener = onItemClickListener
        }

        private lateinit var itemClickListener : OnItemClickListener

        private fun isAnyItemExpanded(position: Int){
            val temp = mList.indexOfFirst {
                it.isExpandable
            }
            if (temp >= 0 && temp != position){
                mList[temp].isExpandable = false
                notifyItemChanged(temp , 0)
            }
        }

        override fun onBindViewHolder(
            holder: menuViewHolder,
            position: Int,
            payloads: MutableList<Any>
        ) {

            if(payloads.isNotEmpty() && payloads[0] == 0){
                holder.collapseExpandedView()
            }else{
                super.onBindViewHolder(holder, position, payloads)

            }
        }

        override fun getItemCount(): Int {
            return mList.size
        }

    }


