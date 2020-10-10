package com.example.contact.screens.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.model.Contact
import com.example.contact.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel(application: Application):AndroidViewModel(application) {

    var allContacts:MutableLiveData<List<Contact>> = MutableLiveData()

    fun requestContact(groupId:Int){
        viewModelScope.launch(Dispatchers.Main){
            allContacts.value = withContext(Dispatchers.IO){
                REPOSITORY.getAllContactOfGroup(groupId)
            }
        }
    }
}