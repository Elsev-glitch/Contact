package com.example.contact.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contact.model.Contact
import com.example.contact.model.Group

@Database(entities = [Group::class, Contact::class], version = 2)
abstract class AppRoomDatabase: RoomDatabase(){
    abstract fun getGroupDao():GroupDao
    abstract fun getContactDao():ContactDao

    companion object {
        @Volatile
        private var database: AppRoomDatabase? = null

        @Synchronized
        fun getDatabase(context: Context):AppRoomDatabase{
            return if (database==null){
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                database as AppRoomDatabase
            } else {
                database as AppRoomDatabase
            }
        }
    }

}