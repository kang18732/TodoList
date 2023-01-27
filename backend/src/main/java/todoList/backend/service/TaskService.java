package todoList.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoList.backend.common.exception.BadRequestException;
import todoList.backend.data.entity.Task;
import todoList.backend.data.entity.User;
import todoList.backend.repository.TaskRepository;
import todoList.backend.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

//    public ResponseEntity<ResponseDto<String>> createBook(Long userId, BookDto.BookInfoDto bookInfoDto) {
//        User user;
//        try {
//            user = userRepository.findById(userId).get();
//        }
//        catch(NoSuchElementException e) {
//            throw new BadRequestException("존재하지 않는 유저입니다.");
//        }
//        bookRepository.save(bookInfoDto.toBookEntity(user));
//
//        ResponseDto<String> responseDto = new ResponseDto<>("새로운 도서가 등록되었습니다.");
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }

    public Task saveTask(String uid, Task task) {
        User user;
        try {
            user = userRepository.findByUid(uid).get();
        } catch (NoSuchElementException e) {
            throw new BadRequestException("존재하지 않는 사용자입니다.");
        }

        return taskRepository.save(task);
    }

//    public Task saveTask(Task task) { return taskRepository.save(task); }

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
