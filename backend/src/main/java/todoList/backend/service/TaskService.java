package todoList.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todoList.backend.common.exception.BadRequestException;
import todoList.backend.data.dto.TaskDto;
import todoList.backend.data.entity.Task;
import todoList.backend.data.entity.User;
import todoList.backend.repository.TaskRepository;
import todoList.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void createTask(Long userId, TaskDto taskDto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new BadRequestException("존재하지 않는 사용자입니다.");
        }

        Task task = Task.builder()
                .text(taskDto.getText())
                .done(false)
                .user(user.get())
                .build();
        taskRepository.save(task);
    }

    public List<TaskDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskDto> taskDtoList = taskList.stream().map(TaskDto::new).collect(Collectors.toList());
        return taskDtoList;
    }

    @Transactional
    public void deleteTask(Long userId, Long taskId) {
        taskRepository.deleteByUserIdAndId(userId, taskId);
    }

    public void updateTask(Long userId, Long taskId, TaskDto taskDto) {
        Optional<Task> task = taskRepository.findByUserIdAndId(userId, taskId);
        if(task.isEmpty()) {
            throw new BadRequestException("존재하지 않는 할일입니다.");
        }

        task.get().setText(taskDto.getText());
        task.get().setDone(taskDto.getDone());
        taskRepository.save(task.get());
    }

//    public List<Task> findTasksByText(String text) {
//        return taskRepository.findAllByText(text);
//    }
//
//    public Boolean removeTask(Long id) {
//        if(taskRepository.findById(id).isPresent()) {
//            taskRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
}
