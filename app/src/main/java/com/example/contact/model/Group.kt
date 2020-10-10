package com.example.contact.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contact.R
import java.io.Serializable

@Entity(tableName = "groups")
data class Group(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id") val id:Int=0,
    val name:String="",
    val text:String="",
    val color:Int = R.color.colorWhite
):Serializable