package com.example.contact.screens.addContact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.contact.model.Contact
import com.example.contact.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactViewModel(application: Application):AndroidViewModel(application){

    fun insertContact(contact:Contact, onSuccess:()->Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.insertContact(contact){
                onSuccess()
            }
        }
    }
}