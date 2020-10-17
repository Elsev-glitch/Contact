package com.example.contact.screens.changeContact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contact.R
import com.example.contact.databinding.FragmentChangeContactBinding
import com.example.contact.model.Contact
import com.example.contact.model.Group
import com.example.contact.utilits.APP_ACTIVITY
import com.example.contact.utilits.REQUEST_PHOTO
import com.example.contact.utilits.picassoPhoto
import com.example.contact.utilits.showToast

class ChangeContactFragment : Fragment() {

    private var _binding: FragmentChangeContactBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: ChangeContactViewModel
    private lateinit var mCurrentGroup: Group
    private lateinit var mContact: Contact
    private var groupId: Int = 0
    private var photoUri: String = "empty"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeContactBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(ChangeContactViewModel::class.java)
        setHasOptionsMenu(true)
        mCurrentGroup = arguments?.getSerializable("group") as Group
        groupId = mCurrentGroup.id
        mContact = arguments?.getSerializable("contact") as Contact
        mBinding.btnContactChange.setOnClickListener {
            updateContact()
        }
        mBinding.inputContactPhoto.setOnClickListener {
            addPhoto()
        }
        mBinding.inputContactName.setText(mContact.name)
        mBinding.inputContactPhone.setText(mContact.phone)
        mBinding.inputContactEmail.setText(mContact.email)
        mBinding.inputContactComent.setText(mContact.comment)
        mBinding.inputContactPhoto.picassoPhoto(mContact.photoUrl)
    }

    private fun updateContact() {
        val name = mBinding.inputContactName.text.toString()
        val phone = mBinding.inputContactPhone.text.toString()
        val email = mBinding.inputContactEmail.text.toString()
        val comment = mBinding.inputContactComent.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.input_name_toast))
        } else {
            mViewModel.updateContact(
                Contact(
                    id = mContact.id,
                    name = name,
                    phone = phone,
                    email = email,
                    comment = comment,
                    photoUrl = photoUri,
                    groupId = groupId
                )
            ) {
                val bundle = Bundle()
                bundle.putSerializable("groupItem", mCurrentGroup)
                APP_ACTIVITY.navController.navigate(
                    R.id.action_changeContactFragment_to_contactFragment,
                    bundle
                )
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
        if (requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
            photoUri = data.data.toString()
            val photo = mBinding.inputContactPhoto
            photo.picassoPhoto(photoUri)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_change, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete -> {
                mViewModel.deleteContact(mContact){
                    val bundle = Bundle()
                    bundle.putSerializable("groupItem", mCurrentGroup)
                    APP_ACTIVITY.navController.navigate(R.id.action_changeContactFragment_to_contactFragment, bundle)
                }
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}