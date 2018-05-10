package dev.kakueki61.todolist.repositories

import dev.kakueki61.todolist.models.Task

interface TaskRepository {
    fun create(content: String): Task
    fun update(task: Task)
    fun findAll(): List<Task>
    fun findById(id: Long): Task?
}
