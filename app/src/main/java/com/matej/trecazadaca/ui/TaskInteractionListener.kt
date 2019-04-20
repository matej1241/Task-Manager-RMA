package com.matej.trecazadaca.ui

import com.matej.trecazadaca.model.Task

interface TaskInteractionListener {
    fun onRemove(task: Task)
}