package com.product_assignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.product_assignment.ui.fragment.FrequentlyOrderedFragment
import com.product_assignment.ui.fragment.RecommendedFragment

/**
 * Created by Gitanjali Ghangale on 10/25/2021.
 */
private const val NUM_TABS = 2
class ViewPagerProductAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return FrequentlyOrderedFragment()
        }
        return RecommendedFragment()
    }
}