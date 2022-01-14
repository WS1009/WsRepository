package com.ws.lib.flow

interface ITaskCreator {
    fun createTask(taskName: String): Task
}