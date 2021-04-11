package com.example.peoplerootstack.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.peoplerootstack.R
import com.example.peoplerootstack.databinding.ActivityMainBinding
import com.example.peoplerootstack.view.main.fragments.FragmentHome
import com.example.peoplerootstack.view.main.fragments.FragmentItems

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var modelView: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modelView = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
    }

    private fun initBottomNavigation(){
        binding.idBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    val fragment = FragmentHome()
                    openFragment(fragment)
                    true
                }
                R.id.nav_explore -> {
                    modelView.titleItem = "EXPLORE"
                    val fragment = FragmentItems()
                    openFragment(fragment)
                    true
                }
                R.id.nav_queues -> {
                    modelView.titleItem = "MY QUEUES"
                    val fragment = FragmentItems()
                    openFragment(fragment)
                    true
                }
                R.id.nav_profile -> {
                    modelView.titleItem = "PROFILE"
                    val fragment = FragmentItems()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        binding.idBottomNavigation.selectedItemId = R.id.nav_home

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentMain.id,fragment)
            .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
            .addToBackStack(null)
            .commit()
    }


}