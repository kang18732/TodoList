package todoList.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoList.backend.domain.Task;
import todoList.backend.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task) { return taskRepository.save(task); }

    public List<Task> findTasks() {
        return taskRepository.findAll();
    }

    public List<Task> findTasksByText(String text) {
        return taskRepository.findAllByText(text);
    }

    public Boolean removeTask(Long id) {
        if(taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
