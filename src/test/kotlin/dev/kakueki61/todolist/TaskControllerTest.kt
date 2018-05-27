package dev.kakueki61.todolist

import dev.kakueki61.todolist.controllers.TaskController
import dev.kakueki61.todolist.models.Task
import dev.kakueki61.todolist.repositories.TaskRepository
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@WebMvcTest(TaskController::class)
class TaskControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var taskRepository: TaskRepository

    @MockBean
    private lateinit var commandLineRunner: CommandLineRunner

    @Test
    fun index_display_all_the_stored_tasks() {
        val tasks = listOf(
                Task(id = 123, content = "hoge", done = false),
                Task(id = 234, content = "fuga", done = true)
        )
        Mockito.`when`(taskRepository.findAll())
                .thenReturn(tasks)
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(view().name("tasks/index"))
                .andExpect(model().attribute("tasks", tasks))
                .andExpect(content().string(Matchers.containsString("<span>hoge</span>")))
                .andExpect(content().string(Matchers.containsString("<s>fuga</s>")))
    }

    @Test
    fun create_creates_tasks() {
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .param("content", "piyo"))
                .andExpect(redirectedUrl("/tasks"))

        Mockito.verify(taskRepository).create("piyo")
    }
}
