package gryszq.dev.angularshop.repository;

import gryszq.dev.angularshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
