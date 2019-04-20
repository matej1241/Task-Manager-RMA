package com.matej.trecazadaca.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.matej.trecazadaca.ui.AddTaskFragment
import com.matej.trecazadaca.ui.TasksFragment

class TasksFragmentAdapter(
    fragmentManager: androidx.fragment.app.FragmentManager): androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {

    val fragments = arrayOf(
        TasksFragment.newInstance(),
        AddTaskFragment.newInstance()
    )

    val titles = arrayOf("Tasks", "Add Tasks")

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}