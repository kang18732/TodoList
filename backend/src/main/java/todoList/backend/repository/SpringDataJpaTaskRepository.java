package todoList.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoList.backend.domain.Task;

public interface SpringDataJpaTaskRepository extends JpaRepository<Task, Long>, TaskRepository {
}
