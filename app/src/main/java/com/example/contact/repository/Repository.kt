package com.example.contact.repository

import androidx.lifecycle.LiveData
import com.example.contact.model.Contact
import com.example.contact.model.Group
import com.example.contact.repository.room.GroupWithContact

interface Repository {
    fun getAllGroupWithContact():List<GroupWithContact>

    val allGroups:LiveData<List<Group>>

    suspend fun insertGroup(group:Group, onSuccess:()->Unit)

    suspend fun updateGroup(group: Group, onSuccess:()->Unit)

    suspend fun deleteGroup(group: Group, onSuccess:()->Unit)

    fun getAllContactOfGroup(group_id:Int): List<Contact>

    fun getCount(group_id:Int):Int

    suspend fun insertContact(contact:Contact, onSuccess:()->Unit)

    suspend fun updateContact(contact: Contact, onSuccess:()->Unit)

    suspend fun deleteContact(contact: Contact, onSuccess:()->Unit)
}