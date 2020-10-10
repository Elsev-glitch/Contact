package com.example.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.contact.databinding.ActivityMainBinding
import com.example.contact.utilits.APP_ACTIVITY

class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding? = null
    private val mBinding get()=_binding!!
    lateinit var navController: NavController
    lateinit var mToolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
    }

    private fun init() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        mToolbar = mBinding.toolbar
        setSupportActionBar(mToolbar)
        APP_ACTIVITY = this
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}