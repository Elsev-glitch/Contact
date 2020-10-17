package com.example.contact.screens.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.model.Contact
import com.example.contact.utilits.picassoPhoto
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(private val itemActionClick:(Contact)->Unit, private val click:(Contact)->Unit) : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    private var listContact = emptyList<Contact>()

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactName: TextView = view.item_contact_name
        val contactPhone: TextView = view.item_contact_phone
        val contactPhoto: CircleImageView = view.item_contact_photo
        val contactCall:ImageView = view.item_contact_call
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactHolder(view)
    }

    override fun getItemCount(): Int = listContact.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.contactName.text = listContact[position].name
        holder.contactPhone.text = listContact[position].phone
        holder.contactPhoto.picassoPhoto(listContact[position].photoUrl)
    }

    override fun onViewAttachedToWindow(holder: ContactHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            click(listContact[holder.adapterPosition])
        }
            holder.contactCall.setOnClickListener { itemActionClick(listContact[holder.adapterPosition]) }

    }

    override fun onViewDetachedFromWindow(holder: ContactHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.contactCall.setOnClickListener(null)
        holder.itemView.setOnClickListener(null)
    }

    fun setList(list: List<Contact>){
        listContact = list
        notifyDataSetChanged()
    }
}