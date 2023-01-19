package todoList.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoList.backend.data.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByText(String text);
}
