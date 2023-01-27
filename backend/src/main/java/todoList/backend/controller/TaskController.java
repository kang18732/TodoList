package todoList.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoList.backend.data.entity.Task;
import todoList.backend.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/task")
    public Task postTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping(value = "/task-list")
    public List<Task> getTaskList() {
        return taskService.findTasks();
    }

    @GetMapping(value = "/task-list/{text}")
    public List<Task> getTaskListByText(@PathVariable String text) {
        return taskService.findTasksByText(text);
    }

    @DeleteMapping(value = "/task/{id}")
    public Boolean deleteTask(@PathVariable Long id) {
        return taskService.removeTask(id);
    }

    // 인자에 HttpServletRequest 추가
//     Long userId = Long.valueOf(String.valueOf(httpServletRequest.getAttribute("id")));
}
