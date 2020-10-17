package com.example.contact.utilits

import android.widget.ImageView
import android.widget.Toast
import com.example.contact.model.Group
import com.squareup.picasso.Picasso

fun showToast(string:String){
    Toast.makeText(APP_ACTIVITY, string, Toast.LENGTH_SHORT).show()
}

fun ImageView.picassoPhoto(Uri:String){
    Picasso.get()
        .load(Uri)
        .centerCrop()
        .fit()
        .into(this)
}