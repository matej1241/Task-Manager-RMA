package com.matej.trecazadaca.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.matej.trecazadaca.MyApplication
import com.matej.trecazadaca.R
import com.matej.trecazadaca.database.TaskDatabase
import com.matej.trecazadaca.model.Task
import com.matej.trecazadaca.ui.TaskInteractionListener
import kotlinx.android.synthetic.main.item_task.view.*


class DisplayTaskAdapter(
    tasks: MutableList<Task>,
    taskListener: TaskInteractionListener
): androidx.recyclerview.widget.RecyclerView.Adapter<TaskHolder>(){

    private var tasks: MutableList<Task>
    private val taskListener: TaskInteractionListener

    init {
        this.tasks = mutableListOf()
        this.tasks.addAll(tasks)
        this.taskListener = taskListener
    }

    fun refreshData(books: MutableList<Task>) {
        this.tasks.clear()
        this.tasks.addAll(books)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val taskView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val taskHolder = TaskHolder(taskView)
        return taskHolder
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, taskListener)
    }
}

class TaskHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    fun bind(task: Task, taskListener: TaskInteractionListener){
        itemView.taskTitle.text = task.title
        itemView.taskDescription.text = task.description
        when(task.priority){
            0 -> itemView.priorityLine.setBackgroundColor(Color.parseColor("#004c10"))
            1 -> itemView.priorityLine.setBackgroundColor(Color.parseColor("#ffe100"))
            2 -> itemView.priorityLine.setBackgroundColor(Color.parseColor("#720000"))
        }
        itemView.setOnLongClickListener{taskListener.onRemove(task);true;}
    }
}


