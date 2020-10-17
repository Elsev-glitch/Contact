package com.example.contact.screens.changeContact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.contact.model.Contact
import com.example.contact.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChangeContactViewModel(application: Application):AndroidViewModel(application) {

    fun updateContact(contact:Contact, onSuccess:()->Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.updateContact(contact){
                onSuccess()
            }
        }
    }

    fun deleteContact(contact: Contact, onSuccess:()->Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.deleteContact(contact){
                onSuccess()
            }
        }
    }
}