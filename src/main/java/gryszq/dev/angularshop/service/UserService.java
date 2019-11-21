package gryszq.dev.angularshop.service;

import gryszq.dev.angularshop.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
