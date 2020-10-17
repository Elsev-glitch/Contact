package com.example.contact.repository.room

import androidx.room.Embedded
import androidx.room.Relation
import com.example.contact.model.Contact
import com.example.contact.model.Group
import java.io.Serializable

class GroupWithContact : Serializable {
    @Embedded
    var group:Group? = null
    @Relation(parentColumn = "id", entityColumn="group_id", entity = Contact::class)
    var contact:List<Contact> = listOf()

    override fun toString(): String {
        return " GroupWithContact" + group.toString()  + " | "+ contact.toString() +" | "
    }
}
