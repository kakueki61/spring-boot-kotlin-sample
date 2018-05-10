package dev.kakueki61.todolist.controllers

import dev.kakueki61.todolist.models.Task
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@RequestMapping("tasks")
class TaskController {
    @GetMapping("")
    fun index(model: Model): String {
        val tasks = listOf(
                Task(1, "clean my room", false),
                Task(2, "buy eggs", true)
        )
        model.addAttribute("tasks", tasks)
        return "tasks/index"
    }
}
