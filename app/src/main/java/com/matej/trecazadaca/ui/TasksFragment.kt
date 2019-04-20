package com.matej.trecazadaca.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matej.trecazadaca.R
import com.matej.trecazadaca.adapter.DisplayTaskAdapter
import com.matej.trecazadaca.database.TaskDatabase
import com.matej.trecazadaca.model.Task

class TasksFragment: androidx.fragment.app.Fragment() {

    private val tasksDao = TaskDatabase.getInstance().taskDao()

    companion object {
        fun newInstance(): TasksFragment {
            return TasksFragment()
        }
        lateinit var taskListener: TaskInteractionListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        super.onStart()
        val tasksDisplay = view?.findViewById(R.id.taskDisplay) as androidx.recyclerview.widget.RecyclerView
        val tasks = getTasks()
        tasksDisplay.layoutManager = LinearLayoutManager(activity)
        setTaskInteractionListener(tasksDisplay)
        tasksDisplay.adapter = DisplayTaskAdapter(tasks, taskListener)
        return view
    }

    private fun setTaskInteractionListener(tasksDisplay: RecyclerView) {
        taskListener = object: TaskInteractionListener{
            override fun onRemove(task: Task) {
                tasksDao.delete(task)
                (tasksDisplay.adapter as DisplayTaskAdapter).refreshData(getTasks())
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser && isResumed()){
            val tasksDisplay = view?.findViewById(R.id.taskDisplay) as androidx.recyclerview.widget.RecyclerView
            val tasks = getTasks()
            tasksDisplay.adapter = DisplayTaskAdapter(tasks, taskListener)
        }
    }

    private fun getTasks(): MutableList<Task>{
        return tasksDao.selectTitle()
    }

}