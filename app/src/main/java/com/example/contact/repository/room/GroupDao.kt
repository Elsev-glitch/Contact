package com.example.contact.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contact.model.Group

@Dao
interface GroupDao{
    @Query("SELECT * from groups")
    fun getAllGroupWithContact():List<GroupWithContact>

    @Query("SELECT * from groups")
    fun getAllGroup():LiveData<List<Group>>

    @Insert
    suspend fun insert(group: Group)

    @Update
    suspend fun update(group: Group)

    @Delete
    suspend fun delete(group: Group)
}