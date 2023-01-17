package todoList.backend.repository;

import todoList.backend.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
//    Optional<Task> findByText(String text);
    List<Task> findAll();
    List<Task> findAllByText(String text);
    void deleteById(Long id);
}
