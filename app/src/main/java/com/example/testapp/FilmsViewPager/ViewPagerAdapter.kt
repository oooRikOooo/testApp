package com.example.testapp.FilmsViewPager

import androidx.fragment.app.*
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm:Fragment):FragmentStateAdapter(fm) {
    private val fragmentTitleList = mutableListOf("Список", "Таблиця")
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                FilmsListFragment()
            }
            1-> {
                FilmsTableFragment()
            } else -> FilmsListFragment()
        }
    }
    /*override fun getCount(): Int = fragmentCount *//*{
        return 2
    }*//*

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FilmsListFragment()
            }
            1-> {
                FilmsTableFragment()
            } else -> FilmsListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    *//*when (position) {
            0 -> "Список"
            1 -> "Таблиця"
            else -> {
                return "Список"
            }
        }*//*
    }*/
}