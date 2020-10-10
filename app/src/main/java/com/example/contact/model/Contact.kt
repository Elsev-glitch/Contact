package com.example.contact.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "group_id") val groupId: Int,
    var name: String ="",
    var phone: String ="",
    var email: String ="",
    var comment: String ="",
    var photoUrl: String =""
):Serializable