package com.matej.trecazadaca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.matej.trecazadaca.MyApplication
import com.matej.trecazadaca.R
import com.matej.trecazadaca.database.TaskDatabase
import com.matej.trecazadaca.model.Task
import kotlinx.android.synthetic.main.fragment_add_task.*

class AddTaskFragment: androidx.fragment.app.Fragment() {

    private val tasksDao = TaskDatabase.getInstance().taskDao()

    companion object {
        fun newInstance(): AddTaskFragment {
            return AddTaskFragment()
        }
        const val LOW_PRIORITY = 0
        const val MEDIUM_PRIORITY = 1
        const val HIGH_PRIORITY = 2
        val priorities = arrayOf("Low priority", "Medium priority", "High priority")
        var spinnerResult = LOW_PRIORITY
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)
        val addTask = view.findViewById(R.id.addTaskAction) as Button
        val spinner = view?.findViewById(R.id.prioritySpinner) as Spinner
        val arrayAdapter = ArrayAdapter(MyApplication.ApplicationContext, android.R.layout.simple_spinner_item, priorities)
        setUpSpinner(spinner, arrayAdapter)
        addTask.setOnClickListener{ addTask() }
        return view
    }

    private fun addTask() {
        val taskTitleResult = taskTitle?.text?.toString() ?: ""
        val taskDescriptionResult = taskDescription?.text?.toString() ?: ""
        val task = Task(0, taskTitleResult, taskDescriptionResult, spinnerResult )
        tasksDao.insert(task)
        taskTitle.text.clear()
        taskDescription.text.clear()
    }

    private fun setUpSpinner(spinner: Spinner, arrayAdapter: ArrayAdapter<String>) {
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                when(selectedItem){
                    priorities[LOW_PRIORITY] -> spinnerResult = LOW_PRIORITY
                    priorities[MEDIUM_PRIORITY] -> spinnerResult = MEDIUM_PRIORITY
                    priorities[HIGH_PRIORITY] -> spinnerResult = HIGH_PRIORITY
                }
            }
        }
    }
}
