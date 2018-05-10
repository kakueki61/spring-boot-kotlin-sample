package dev.kakueki61.todolist.repositories

import dev.kakueki61.todolist.models.Task
import org.springframework.stereotype.Repository

@Repository
class InMemoryTaskRepository : TaskRepository {

    private val tasks: MutableList<Task> = mutableListOf()
    private val maxId: Long
        get() = tasks.map(Task::id).max() ?: 0

    override fun create(content: String): Task {
        return Task(maxId + 1, content, false).also { tasks += it }
    }

    override fun update(task: Task) {
        tasks.replaceAll {
            if (it.id == task.id) task
            else it
        }
    }

    override fun findAll(): List<Task> = tasks.toList()

    override fun findById(id: Long): Task? {
        return tasks.find { it.id == id }
    }

}
