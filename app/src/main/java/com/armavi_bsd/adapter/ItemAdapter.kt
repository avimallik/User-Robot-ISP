package com.armavi_bsd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.armavi_bsd.model.ItemMenu
import com.armavi_bsd.userrobotisp.R

class ItemAdapter(private val items: List<ItemMenu>, private val onItemClick: (ItemMenu,Int) -> Unit)
    : RecyclerView.Adapter <ItemAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTitle: TextView = view.findViewById(R.id.itemTitle)
        val itemIcon: ImageView = view.findViewById(R.id.itemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemTitle.text = item.menuTitle
        holder.itemIcon.setImageResource(item.menuIcon)

        // Set click listener with position
        holder.itemView.setOnClickListener {
            onItemClick(item, position) // Pass the item and its position
        }
    }

    override fun getItemCount(): Int = items.size

}