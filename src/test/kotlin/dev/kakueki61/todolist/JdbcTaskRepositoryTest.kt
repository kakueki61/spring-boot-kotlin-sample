package dev.kakueki61.todolist

import dev.kakueki61.todolist.repositories.JdbcTaskRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@Sql(statements = ["DELETE FROM task"])
class JdbcTaskRepositoryTest {
    @Autowired
    private lateinit var sut: JdbcTaskRepository

    @Test
    fun findAll_returns_nothing_before_creating_task() {
        val got = sut.findAll()
        assertThat(got).isEmpty()
    }

    @Test
    fun findById_returns_the_created_task() {
        val task = sut.create("TEST")
        val got = sut.findById(task.id)
        assertThat(got).isEqualTo(task)
    }
}
