package com.example.contact.screens.addContact

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
import com.example.contact.utilits.showToast

class AddContactFragment : Fragment() {

    private var _binding: FragmentAddContactBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel:AddContactViewModel
//    private lateinit var mCurrentGroup:Group

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
        val mCurrentGroup:Group = arguments?.getSerializable("group") as Group
        val group_id = mBinding.groupId
        group_id.text = mCurrentGroup.id.toString()
        val groupId = mCurrentGroup.id
        mBinding.btnContactAdd.setOnClickListener {
            val name = mBinding.inputContactName.text.toString()
            val phone = mBinding.inputContactPhone.text.toString()
            val email = mBinding.inputContactEmail.text.toString()
            val comment = mBinding.inputContactComent.text.toString()
            if (name.isEmpty()){
                showToast(getString(R.string.input_name_toast))
            } else {
                mViewModel.insertContact(Contact(name = name, phone = phone, email = email, comment = comment, groupId = groupId!!)){
                    val bundle = Bundle()
                    bundle.putSerializable("groupItem", mCurrentGroup)
                    APP_ACTIVITY.navController.navigate(R.id.action_addContactFragment_to_contactFragment, bundle)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}