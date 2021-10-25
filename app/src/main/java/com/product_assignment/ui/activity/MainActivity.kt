package com.product_assignment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.product_assignment.R
import com.product_assignment.adapter.ViewPagerProductAdapter
import com.product_assignment.databinding.ActivityMainBinding

val productArray= arrayOf("FrequentlyOrdered","Recommended")

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            val mAdapter=ViewPagerProductAdapter(supportFragmentManager,lifecycle)
            binding.viewPager.adapter=mAdapter
            TabLayoutMediator(binding.tabs,binding.viewPager){tab,position->
                tab.text= productArray[position]
            }.attach()
        }
    }//onCreate
}