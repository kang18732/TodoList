package todoList.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoList.backend.data.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByText(String text);
    void deleteByUserIdAndId(Long userId, Long taskId);
    Optional<Task> findByUserIdAndId(Long userId, Long taskId);
}
