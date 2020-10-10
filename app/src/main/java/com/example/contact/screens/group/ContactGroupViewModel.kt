package com.example.contact.screens.group

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.model.Group
import com.example.contact.repository.room.AppRoomDatabase
import com.example.contact.repository.room.GroupWithContact
import com.example.contact.repository.room.RoomRepository
import com.example.contact.utilits.REPOSITORY
import kotlinx.coroutines.*
import java.io.Serializable

class ContactGroupViewModel(application: Application):AndroidViewModel(application){
    private val mContext = application
    var groupWithContact:MutableLiveData<List<GroupWithContact>> = MutableLiveData()

//    override val coroutineContext: CoroutineContext
//        get() = Dispatchers.Main+job
//    private var job:Job = Job()

    fun initDatabase() {
        val groupDao = AppRoomDatabase.getDatabase(mContext).getGroupDao()
        val contactDao = AppRoomDatabase.getDatabase(mContext).getContactDao()
        REPOSITORY = RoomRepository(groupDao, contactDao)
    }


    fun insertGroup(group: Group, onSuccess:()-> Unit){
            viewModelScope.launch(Dispatchers.IO) {
                REPOSITORY.insertGroup(group) {
                    onSuccess()
                    requestGroupWithContact()
            }
        }
    }


    fun requestGroupWithContact(){
        viewModelScope.launch (Dispatchers.Main){
            groupWithContact.value = withContext(Dispatchers.IO){
                REPOSITORY.getAllGroupWithContact()
            }
        }
    }

    fun deleteGroupWithContact(group: Group, onSuccess:()->Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.deleteGroup(group) {
                onSuccess()
            }
        }
    }

//    val allGroup = REPOSITORY.allGroups
}