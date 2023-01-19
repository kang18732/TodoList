package todoList.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoList.backend.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);
}
