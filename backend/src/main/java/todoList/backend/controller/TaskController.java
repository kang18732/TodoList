package todoList.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoList.backend.domain.Task;
import todoList.backend.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/post/task")
    public Task postTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping(value = "/get/task-list")
    public List<Task> getTaskList() {
        return taskService.findTasks();
    }

    @GetMapping(value = "/get/task-list/{text}")
    public List<Task> getTaskListByText(@PathVariable String text) {
        return taskService.findTasksByText(text);
    }

    @DeleteMapping(value = "/delete/task/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.removeTask(id);
        return "task " + id + " deleted";
    }

    @PutMapping(value = "/put/task")
    public Task updateTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }
}
