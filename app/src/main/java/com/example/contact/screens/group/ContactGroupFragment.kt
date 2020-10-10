package com.example.contact.screens.group

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.databinding.FragmentContactGroupBinding
import com.example.contact.model.Group
import com.example.contact.screens.addContact.AddContactFragment
import com.example.contact.screens.addContact.AddContactViewModel
import com.example.contact.utilits.APP_ACTIVITY
import kotlinx.android.synthetic.main.dialog.view.*


class ContactGroupFragment : Fragment() {

    private var _binding:FragmentContactGroupBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: ContactGroupViewModel
    private lateinit var mRecycleView:RecyclerView
    private lateinit var mAdapter: ContactGroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactGroupBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ContactGroupViewModel::class.java)
        mViewModel.initDatabase()
        mViewModel.requestGroupWithContact()
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        setHasOptionsMenu(true)
        mRecycleView = mBinding.recycleView
        mAdapter = ContactGroupAdapter(mViewModel)
        mRecycleView.adapter = mAdapter
        mViewModel.groupWithContact.observe(this, Observer {
            mAdapter.setList(it)
        })
        mBinding.fab.setOnClickListener {
            createGroupDialog()
        }
    }

    private fun createGroupDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog, null)
        val builder = AlertDialog.Builder(APP_ACTIVITY)
            .setTitle(getString(R.string.dialog_title))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.dialog_positiv_button)){dialogInterface, i ->
                val name = dialogView.input_name_group.text.toString()
                val text = dialogView.input_text_group.text.toString()
                val color = dialogView.color_picker_view.selectedColor
                mViewModel.insertGroup(Group(name = name, text = text, color = color)){
                    dialogInterface.dismiss()
                }
            }
            .setNegativeButton(getString(R.string.dialog_negative)){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .create()
        val alertDialog = builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_app, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_settings -> {
                Toast.makeText(context, "ок", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    companion object{
        fun itemClick(group:Group){
            val bundle = Bundle()
            bundle.putSerializable("groupItem", group)
            APP_ACTIVITY.navController.navigate(R.id.action_contactGroupFragment_to_contactFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}