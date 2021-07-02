package com.example.agendabootcamp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class ContactAdapter(private val contactsList: ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(contact: Contact){
            val txt_name = itemView.findViewById(R.id.contact_name) as TextView
            val txt_phone = itemView.findViewById(R.id.contact_phone) as TextView

            txt_name.text = contact.name
            txt_phone.text = contact.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        holder.bindItem(contactsList[position])
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

}