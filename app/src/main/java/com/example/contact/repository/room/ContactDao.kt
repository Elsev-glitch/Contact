package com.example.contact.repository.room

import androidx.room.*
import com.example.contact.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * from contacts WHERE group_id=:groupId")
    fun getAllContactOfGroup(groupId: Int):List<Contact>

    @Query("SELECT COUNT(id) from contacts WHERE group_id=:groupId")
    fun getCount(groupId:Int):Int

    @Query("DELETE FROM contacts WHERE group_id=:groupId")
    fun deleteContactsByGroup(groupId:Int):Int

    @Insert
    fun insert(contact:Contact)

    @Update
    fun update(contact:Contact)

    @Delete
    fun delete(contact:Contact)
}