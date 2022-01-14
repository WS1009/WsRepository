package com.ws.lib.taskflow

interface ITaskCreator {
    fun createTask(taskName: String): Task
}