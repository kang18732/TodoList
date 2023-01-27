package todoList.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoList.backend.data.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);
    Optional<User> findByUid(String uid);
}
