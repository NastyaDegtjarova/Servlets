package ua.goit.service;

import ua.goit.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    void save(User entity);

    void delete(Long id);

    User update(User entity);

    User findUserByUsername(String username);

}
