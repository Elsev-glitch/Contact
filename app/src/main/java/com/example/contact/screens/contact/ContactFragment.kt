package com.example.contact.screens.contact

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.databinding.FragmentContactBinding
import com.example.contact.model.Contact
import com.example.contact.model.Group
import com.example.contact.utilits.APP_ACTIVITY

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: ContactViewModel
    private lateinit var mRecycleView: RecyclerView
    private lateinit var mAdapter: ContactAdapter
    private lateinit var mCurrentGroup: Group

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        mCurrentGroup = arguments?.getSerializable("groupItem") as Group
        mViewModel.requestContact(mCurrentGroup.id)
        mRecycleView = mBinding.contactRecycleView
        mAdapter = ContactAdapter({callContact(it)},{updateContact(it)})
        mRecycleView.adapter = mAdapter
        mViewModel.allContacts.observe(this, Observer {
            mAdapter.setList(it)
        })
        mBinding.fabContact.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("group", mCurrentGroup)
            APP_ACTIVITY.navController.navigate(
                R.id.action_contactFragment_to_addContactFragment,
                bundle
            )
        }
    }

        private fun callContact(contact: Contact) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.phone))
            startActivity(intent)
        }

        private fun updateContact(contact: Contact) {
            val bundle = Bundle()
            bundle.putSerializable("group", mCurrentGroup)
            bundle.putSerializable("contact", contact)
            APP_ACTIVITY.navController.navigate(
                R.id.action_contactFragment_to_changeContactFragment,
                bundle
            )
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}