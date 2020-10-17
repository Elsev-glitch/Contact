package com.example.contact.repository.room

import androidx.lifecycle.LiveData
import com.example.contact.model.Contact
import com.example.contact.model.Group
import com.example.contact.repository.Repository

class RoomRepository(private val groupDao: GroupDao, private val contactDao:ContactDao):Repository {
    override fun getAllGroupWithContact(): List<GroupWithContact> {
        return groupDao.getAllGroupWithContact()
    }

    override val allGroups: LiveData<List<Group>>
        get() = groupDao.getAllGroup()

    override suspend fun insertGroup(group: Group, onSuccess:()->Unit) {
        groupDao.insert(group)
        onSuccess()
    }

    override suspend fun updateGroup(group: Group, onSuccess:()->Unit) {
        groupDao.update(group)
        onSuccess()
    }

    override suspend fun deleteGroup(group: Group, onSuccess: () -> Unit) {
        groupDao.delete(group)
        contactDao.deleteContactsByGroup(group.id)
        onSuccess()
    }

    override fun getAllContactOfGroup(group_id: Int): List<Contact>{
        return contactDao.getAllContactOfGroup(group_id)
    }

    override fun getCount(group_id: Int): Int {
        return contactDao.getCount(group_id)
    }


    override suspend fun insertContact(contact: Contact, onSuccess: () -> Unit) {
        contactDao.insert(contact)
        onSuccess()
    }

    override suspend fun updateContact(contact: Contact, onSuccess: () -> Unit) {
        contactDao.update(contact)
        onSuccess()
    }

    override suspend fun deleteContact(contact: Contact, onSuccess: () -> Unit) {
        contactDao.delete(contact)
        onSuccess()
    }
}