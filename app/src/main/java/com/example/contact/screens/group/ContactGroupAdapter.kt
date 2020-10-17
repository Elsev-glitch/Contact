package com.example.contact.screens.group

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.model.Group
import com.example.contact.repository.room.GroupWithContact
import kotlinx.android.synthetic.main.item_contact_group.view.*

class ContactGroupAdapter(private val onDeleteClick:(Group)-> Unit ):RecyclerView.Adapter<ContactGroupAdapter.ContactGroupHolder>() {

    private var listItems= emptyList<GroupWithContact>()

    class ContactGroupHolder(view:View):RecyclerView.ViewHolder(view){
        val itemName:TextView = view.item_name
        val itemText:TextView = view.item_text
        val card: CardView = view.card
        val delete:ImageView = view.delete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactGroupHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact_group, parent, false)
        return ContactGroupHolder(view)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ContactGroupHolder, position: Int) {
        holder.itemName.text = listItems[position].group?.name
        holder.itemText.text = listItems[position].group?.text
        val hexColor = "#${listItems[position].group?.color.let { Integer.toHexString(it!!) }}"
        holder.card.setCardBackgroundColor(Color.parseColor(hexColor))
    }

    fun setList(list: List<GroupWithContact>){
        listItems = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ContactGroupHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            ContactGroupFragment.itemClick(listItems[holder.adapterPosition].group!!)
        }

        holder.delete.setOnClickListener {
            onDeleteClick(listItems[holder.adapterPosition].group!!)
        }
    }

    override fun onViewDetachedFromWindow(holder: ContactGroupHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}