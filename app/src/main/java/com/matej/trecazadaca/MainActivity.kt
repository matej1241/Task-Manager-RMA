package com.matej.trecazadaca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matej.trecazadaca.adapter.TasksFragmentAdapter
import com.matej.trecazadaca.database.TaskDatabase
import com.matej.trecazadaca.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tasksDao = TaskDatabase.getInstance().taskDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }

    private fun setUpUi() {
        viewPager.adapter = TasksFragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}
