package gryszq.dev.angularshop.service;

import gryszq.dev.angularshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> findAll();

    void deleteUser(Long id);

    Optional<User> getUserById(Long id);
}
