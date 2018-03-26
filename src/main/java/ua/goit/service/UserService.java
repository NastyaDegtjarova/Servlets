package ua.goit.service;

import ua.goit.model.User;
import ua.goit.service.impl.BaseServise;

import java.util.List;

public interface UserService extends BaseServise<User> {
    List<User> getAll();

    User getById(Long id);

    void save(User entity);

    void delete(Long id);

    User update(User entity);

    User findUserByUsername(String username);

}
