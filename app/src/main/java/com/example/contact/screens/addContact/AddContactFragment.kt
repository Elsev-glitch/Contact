package com.example.contact.screens.addContact

import android.app.Activity.RESULT_OK
import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contact.R
import com.example.contact.databinding.FragmentAddContactBinding
import com.example.contact.model.Contact
import com.example.contact.model.Group
import com.example.contact.utilits.APP_ACTIVITY
import com.example.contact.utilits.REQUEST_PHOTO
import com.example.contact.utilits.picassoPhoto
import com.example.contact.utilits.showToast
import com.squareup.picasso.Picasso

class AddContactFragment : Fragment() {

    private var _binding: FragmentAddContactBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel:AddContactViewModel
    private lateinit var mCurrentGroup:Group
    private var groupId:Int = 0
    private var photoUri:String = "empty"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddContactBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(AddContactViewModel::class.java)
        mCurrentGroup= arguments?.getSerializable("group") as Group
        groupId = mCurrentGroup.id
        mBinding.btnContactAdd.setOnClickListener {
            addContact()
        }
        mBinding.inputContactPhoto.setOnClickListener {
            addPhoto()
        }
    }

    private fun addContact() {
        val name = mBinding.inputContactName.text.toString()
        val phone = mBinding.inputContactPhone.text.toString()
        val email = mBinding.inputContactEmail.text.toString()
        val comment = mBinding.inputContactComent.text.toString()
        if (name.isEmpty()){
            showToast(getString(R.string.input_name_toast))
        } else {
            mViewModel.insertContact(Contact(name = name, phone = phone, email = email, comment = comment, photoUrl = photoUri, groupId = groupId)){
                val bundle = Bundle()
                bundle.putSerializable("groupItem", mCurrentGroup)
                APP_ACTIVITY.navController.navigate(R.id.action_addContactFragment_to_contactFragment, bundle)
            }
        }
    }

    private fun addPhoto() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK && data != null){
            photoUri = data.data.toString()
            val photo = mBinding.inputContactPhoto
            photo.picassoPhoto(photoUri)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}