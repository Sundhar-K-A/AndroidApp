package com.example.androidapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.R
import com.example.androidapp.database.Item

class ItemAdapter(private var items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemId: TextView = itemView.findViewById(R.id.tvItemId)
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
        val tvItemPrice: TextView = itemView.findViewById(R.id.tvItemPrice)
        val tvItemQuantity: TextView = itemView.findViewById(R.id.tvItemQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_row_layout, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.tvItemId.text = "ID: ${item.itemId}"
        holder.tvItemName.text = "Name: ${item.itemName}"
        holder.tvItemPrice.text = "Price: $${item.itemPrice}"
        holder.tvItemQuantity.text = "Quantity: ${item.quantityInStock}"
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}