package com.example.contact.screens.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.model.Contact
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    private var listContact = emptyList<Contact>()

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactName: TextView = view.item_contact_name
        val contactPhone: TextView = view.item_contact_phone
        val contactPhoto: CircleImageView = view.item_contact_photo

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactHolder(view)
    }

    override fun getItemCount(): Int = listContact.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.contactName.text = listContact[position].name
        holder.contactPhone.text = listContact[position].phone
    }

    fun setList(list: List<Contact>){
        listContact = list
        notifyDataSetChanged()
    }
}