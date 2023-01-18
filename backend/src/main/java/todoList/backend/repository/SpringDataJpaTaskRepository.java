package todoList.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoList.backend.data.entity.Task;

import java.util.List;

public interface SpringDataJpaTaskRepository extends JpaRepository<Task, Long>, TaskRepository {

    @Override
    List<Task> findAllByText(String text);
}
