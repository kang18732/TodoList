package todoList.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
//    public String postTask(@RequestBody Task task) {
//        taskService.saveTask(task);
//        return task.toString();
//    }

    @GetMapping(value = "/get/task-list")
    public List<Task> getTaskList() {
        return taskService.findTasks();
    }
}
